package ro.nicolaemariusghergu.easylearn.datamanager.service.impl;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ro.nicolaemariusghergu.easylearn.datamanager.domain.Author;
import ro.nicolaemariusghergu.easylearn.datamanager.service.AuthorService;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashSet;
import java.util.Set;

@Slf4j
@Service
public class AuthorServiceImpl implements AuthorService {

    private static final HttpClient HTTP_CLIENT = HttpClient.newHttpClient();
    private Set<Author> AVAILABLE_AUTHORS = new HashSet<>();

    @SneakyThrows
    @Override
    public Set<Author> extractAuthorsFromRobmiles(String url) {
        long authorId = 1L;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        HttpResponse<String> HTTP_RESPONSE = HTTP_CLIENT.send(request,
                HttpResponse.BodyHandlers.ofString());

        JSONArray jsonArray = new JSONArray(HTTP_RESPONSE.body());

        Set<Author> authors = new HashSet<>();

        for (int fields = 0; fields < jsonArray.length(); fields++) {
            var jsonEntries = jsonArray.get(fields);
            var jsonObject = new JSONObject(jsonEntries.toString());
            var objectAuthors = jsonObject.getJSONObject("author");
            var authorName = objectAuthors.get("displayName").toString();
            if (!authors.contains(Author.builder().name(authorName).build())) {
                Author author = Author.builder()
                        .id(authorId++)
                        .name(authorName)
                        .build();
                authors.add(author);
            }
        }

        AVAILABLE_AUTHORS = authors;

        return authors;
    }

    @Override
    public ResponseEntity<Set<Author>> getAuthors() {
        return ResponseEntity.ok(AVAILABLE_AUTHORS);
    }


}

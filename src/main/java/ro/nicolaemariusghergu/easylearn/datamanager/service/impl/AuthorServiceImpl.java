package ro.nicolaemariusghergu.easylearn.datamanager.service.impl;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;
import ro.nicolaemariusghergu.easylearn.datamanager.domain.Author;
import ro.nicolaemariusghergu.easylearn.datamanager.service.AuthorService;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
public class AuthorServiceImpl implements AuthorService {

    private static final HttpClient HTTP_CLIENT = HttpClient.newHttpClient();
    private Set<String> INSERTS = new HashSet<>();

    @SneakyThrows
    @Override
    public List<Author> extractAuthorsFromRobmiles(String url) {
        long authorId = 1L;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        HttpResponse<String> HTTP_RESPONSE = HTTP_CLIENT.send(request,
                HttpResponse.BodyHandlers.ofString());

        JSONArray jsonArray = new JSONArray(HTTP_RESPONSE.body());

        List<Author> categories = new ArrayList<>();

        for (int fields = 0; fields < jsonArray.length(); fields++) {
            var jsonEntries = jsonArray.get(fields);
            var jsonObject = new JSONObject(jsonEntries.toString());
            var objectAuthors = jsonObject.getJSONObject("author");
            var authorName = objectAuthors.get("displayName").toString();
            Author author = Author.builder()
                    .id(authorId++)
                    .name(authorName)
                    .build();
            categories.add(author);
        }

        return categories;
    }

    @Override
    public void createInserts(List<Author> authors) {
        Set<String> inserts = new HashSet<>();
        authors.forEach(author -> inserts.add("INSERT INTO authors ('name') VALUES ('" + author.getName() + "')"));
        INSERTS = inserts;
    }

    @Override
    public Set<String> getInserts() {
        return INSERTS;
    }
}

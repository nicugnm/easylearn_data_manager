package ro.nicolaemariusghergu.easylearn.datamanager.service.impl;

import lombok.SneakyThrows;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ro.nicolaemariusghergu.easylearn.datamanager.domain.PublishHouse;
import ro.nicolaemariusghergu.easylearn.datamanager.service.PublishHouseService;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashSet;
import java.util.Set;

@Service
public class PublishHouseServiceImpl implements PublishHouseService {

    private static final HttpClient HTTP_CLIENT = HttpClient.newHttpClient();
    private Set<PublishHouse> AVAILABLE_PUBLISH_HOUSES = new HashSet<>();

    @SneakyThrows
    @Override
    public Set<PublishHouse> extractPublishHousesFromRobmiles(String url) {
//        long pub = 1L;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        HttpResponse<String> HTTP_RESPONSE = HTTP_CLIENT.send(request,
                HttpResponse.BodyHandlers.ofString());

        JSONArray jsonArray = new JSONArray(HTTP_RESPONSE.body());

        Set<PublishHouse> publishHouses = new HashSet<>();

        for (int fields = 0; fields < jsonArray.length(); fields++) {
            var jsonEntries = jsonArray.get(fields);
            var jsonObject = new JSONObject(jsonEntries.toString());
            var objectAuthors = jsonObject.getJSONObject("author");
            var publishHouseName = objectAuthors.get("displayName").toString();
            if (!publishHouses.contains(PublishHouse.builder().name(publishHouseName).build())) {
                PublishHouse publishHouse = PublishHouse.builder()
                        //.id(authorId++)
                        .name(publishHouseName)
                        .build();
                publishHouses.add(publishHouse);
            }
        }

        AVAILABLE_PUBLISH_HOUSES = publishHouses;

        return publishHouses;
    }

    @Override
    public ResponseEntity<Set<PublishHouse>> getAvailablePublishHouses() {
        return ResponseEntity.ok(AVAILABLE_PUBLISH_HOUSES);
    }
}

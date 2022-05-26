package ro.nicolaemariusghergu.easylearn.datamanager.service.impl;


import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ro.nicolaemariusghergu.easylearn.datamanager.domain.Category;
import ro.nicolaemariusghergu.easylearn.datamanager.service.CategoryService;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashSet;
import java.util.Set;

@Slf4j
@Service
public class CategoryServiceImpl implements CategoryService {

    private static final HttpClient HTTP_CLIENT = HttpClient.newHttpClient();
    private Set<Category> AVAILABLE_CATEGORIES = new HashSet<>();

    @SneakyThrows
    @Override
    public Set<Category> extractCategoriesFromRobmiles(String url) {
        long categoryId = 1L;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        HttpResponse<String> HTTP_RESPONSE = HTTP_CLIENT.send(request,
                HttpResponse.BodyHandlers.ofString());

        JSONArray jsonArray = new JSONArray(HTTP_RESPONSE.body());

        Set<Category> categories = new HashSet<>();

        for (int fields = 0; fields < jsonArray.length(); fields++) {
            var jsonEntries = jsonArray.get(fields);
            var jsonObject = new JSONObject(jsonEntries.toString());
            var objectCategories = jsonObject.getJSONArray("categories");
            var categoryName = objectCategories.get(0).toString();
            if (!categories.contains(Category.builder().name(categoryName).build())) {
                Category category = Category.builder()
                        .id(categoryId++)
                        .name(categoryName)
                        .build();
                categories.add(category);
            }
        }

        AVAILABLE_CATEGORIES = categories;

        return categories;
    }

    @Override
    public ResponseEntity<Set<Category>> getCategories() {
        return ResponseEntity.ok(AVAILABLE_CATEGORIES);
    }
}

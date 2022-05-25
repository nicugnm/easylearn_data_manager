package ro.nicolaemariusghergu.easylearn.datamanager.service.impl;


import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;
import ro.nicolaemariusghergu.easylearn.datamanager.domain.Category;
import ro.nicolaemariusghergu.easylearn.datamanager.service.CategoryService;

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
public class CategoryServiceImpl implements CategoryService {

    private static final HttpClient HTTP_CLIENT = HttpClient.newHttpClient();
    private Set<String> INSERTS = new HashSet<>();

    @SneakyThrows
    @Override
    public List<Category> extractCategoriesFromRobmiles(String url) {
        long categoryId = 1L;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        HttpResponse<String> HTTP_RESPONSE = HTTP_CLIENT.send(request,
                HttpResponse.BodyHandlers.ofString());

        JSONArray jsonArray = new JSONArray(HTTP_RESPONSE.body());

        List<Category> categories = new ArrayList<>();

        for (int fields = 0; fields < jsonArray.length(); fields++) {
            var jsonEntries = jsonArray.get(fields);
            var jsonObject = new JSONObject(jsonEntries.toString());
            var objectCategories = jsonObject.getJSONArray("categories");
            var categoryName = objectCategories.get(0).toString();
            Category category = Category.builder()
                    .id(categoryId++)
                    .name(categoryName)
                    .build();
            categories.add(category);
        }

        return categories;
    }

    @Override
    public void createInserts(List<Category> categories) {
        Set<String> inserts = new HashSet<>();
        categories.forEach(category -> inserts.add("INSERT INTO categories ('name') VALUES ('" + category.getName() + "')"));
        INSERTS = inserts;
    }

    @Override
    public Set<String> getInserts() {
        return INSERTS;
    }
}

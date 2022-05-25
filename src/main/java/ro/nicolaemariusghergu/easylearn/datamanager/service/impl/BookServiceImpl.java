package ro.nicolaemariusghergu.easylearn.datamanager.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;
import ro.nicolaemariusghergu.easylearn.datamanager.config.ProductProperties;
import ro.nicolaemariusghergu.easylearn.datamanager.domain.*;
import ro.nicolaemariusghergu.easylearn.datamanager.service.BookService;

import java.math.BigDecimal;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;

@Slf4j
@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {

    private static final HttpClient HTTP_CLIENT = HttpClient.newHttpClient();
    private Set<String> INSERTS = new HashSet<>();

    private final ProductProperties productProperties;

    private static final Long ID_UNAVAILABLE = 0L;
    private static final Long ID_IN_STOCK = 1L;


    @SneakyThrows
    @Override
    public List<Book> extractBooksFromRobmiles(String url, List<Category> categories, List<Author> authors) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        HttpResponse<String> HTTP_RESPONSE = HTTP_CLIENT.send(request,
                HttpResponse.BodyHandlers.ofString());

        JSONArray jsonArray = new JSONArray(HTTP_RESPONSE.body());

        List<Book> books = new ArrayList<>();

        for (int fields = 0; fields < jsonArray.length(); fields++) {
            var jsonEntries = jsonArray.get(fields);
            var jsonObject = new JSONObject(jsonEntries.toString());

            var objectCategories = jsonObject.getJSONArray("categories");
            var categoryName = objectCategories.get(0).toString();

            var categoryId = categories.stream()
                    .filter(category -> category.getName().equals(categoryName))
                    .map(AbstractEntity::getId)
                    .findFirst()
                    .get();

            var objectAuthors = jsonObject.getJSONObject("author");
            var authorName = objectAuthors.get("displayName").toString();

            var authorId = authors.stream()
                    .filter(author -> author.getName().equals(authorName))
                    .map(AbstractEntity::getId)
                    .findFirst()
                    .get();

            var iconUrl = String.valueOf(jsonObject.get("assetUrl"));

            var random = new Random();
            var randomValue = random.nextInt(0, 2);
            var randomStock = random.nextInt(1, 50);

            Book book = Book.builder()
                    .category(Category.builder()
                            .id(categoryId)
                            .name(categoryName)
                            .build())
                    .publishHouse(PublishHouse.builder()
                            .id(authorId)
                            .name(authorName)
                            .build())
                    .author(Author.builder()
                            .id(authorId)
                            .name(authorName)
                            .build())
                    .discount(Discount.builder()
                            .id(1L)
                            .value(BigDecimal.ZERO)
                            .build())
                    .priceType(PriceType.builder()
                            .id(productProperties.getPriceTypes().get(randomValue).getId())
                            .value(productProperties.getPriceTypes().get(randomValue).getValue())
                            .build())
                    .status(Status.builder()
                            .id(productProperties.getStatus().get(ID_IN_STOCK.intValue()).getId())
                            .statusType(productProperties.getStatus().get(ID_IN_STOCK.intValue()).getStatusType())
                            .build())
                    .stockCount(randomStock)
                    .iconUrl(iconUrl)
                    .build();

            if (book.getStockCount() == 0) {
                book.setStatus(Status.builder()
                                .id(productProperties.getStatus().get(ID_UNAVAILABLE.intValue()).getId())
                        .statusType(productProperties.getStatus().get(ID_UNAVAILABLE.intValue()).getStatusType())
                        .build());
            }

            books.add(book);
        }

        return books;
    }

    @Override
    public void createInserts(List<Book> books) {
        Set<String> inserts = new HashSet<>();
        books.forEach(book -> inserts.add("INSERT INTO books ('category_id', 'status_id', 'discount_id', 'price_type_id', 'publish_house_id', 'stock_count', 'icon_url', 'author_id')" + " VALUES ('" +
                        book.getCategory().getId() + "', '" +
                book.getStatus().getId() + "', '" +
                book.getDiscount().getId() + "', '" +
                book.getPriceType().getId() + "', '" +
                book.getPublishHouse().getId() + "', '" +
                book.getStockCount() + "', '" +
                book.getIconUrl() + "', '" +
                book.getAuthor().getId() + "')"
        ));
        INSERTS = inserts;
    }

    @Override
    public Set<String> getInserts() {
        return INSERTS;
    }
}

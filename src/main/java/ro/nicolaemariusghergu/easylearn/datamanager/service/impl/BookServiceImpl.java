package ro.nicolaemariusghergu.easylearn.datamanager.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ro.nicolaemariusghergu.easylearn.datamanager.config.ProductProperties;
import ro.nicolaemariusghergu.easylearn.datamanager.domain.*;
import ro.nicolaemariusghergu.easylearn.datamanager.service.BookService;

import java.math.BigDecimal;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Slf4j
@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {

    private static final HttpClient HTTP_CLIENT = HttpClient.newHttpClient();
    private static final Long ID_UNAVAILABLE = 0L;
    private static final Long ID_IN_STOCK = 1L;
    private static final String VALUE_NOT_FREE = "NOT FREE";
    private final ProductProperties productProperties;
    private Set<Book> AVAILABLE_BOOKS = new HashSet<>();

    @SneakyThrows
    @Override
    public void extractBooksFromRobmiles(String url, Set<Category> categories, Set<Author> authors) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        HttpResponse<String> HTTP_RESPONSE = HTTP_CLIENT.send(request,
                HttpResponse.BodyHandlers.ofString());

        JSONArray jsonArray = new JSONArray(HTTP_RESPONSE.body());

        Set<Book> books = new HashSet<>();

        for (int fields = 0; fields < jsonArray.length(); fields++) {
            var jsonEntries = jsonArray.get(fields);
            var jsonObject = new JSONObject(jsonEntries.toString());

            var objectCategories = jsonObject.getJSONArray("categories");
            var categoryName = objectCategories.get(0).toString();

//            var categoryId = categories.stream()
//                    .filter(category -> category.getName().equals(categoryName))
//                    .map(AbstractEntity::getId)
//                    .findFirst()
//                    .get();

            var objectAuthors = jsonObject.getJSONObject("author");
            var authorName = objectAuthors.get("displayName").toString();

//            var authorId = authors.stream()
//                    .filter(author -> author.getName().equals(authorName))
//                    .map(AbstractEntity::getId)
//                    .findFirst()
//                    .get();

            var iconUrl = String.valueOf(jsonObject.get("assetUrl"));

            var title = String.valueOf(jsonObject.get("title"));

            var random = new Random();
            var randomValue = random.nextInt(0, 2);
            var randomStock = random.nextInt(1, 50);
            var randomPrice = random.nextInt(1, 20);

            if (!books.contains(Book.builder().title(title).build())) {
                Book book = Book.builder()
                        .title(title)
                        .category(Category.builder()
 //                               .id(categoryId)
                                .name(categoryName)
                                .build())
                        .publishHouse(PublishHouse.builder()
   //                             .id(authorId)
                                .name(authorName)
                                .build())
                        .author(Author.builder()
     //                           .id(authorId)
                                .name(authorName)
                                .build())
                        .discount(Discount.builder()
       //                         .id(1L)
                                .value(BigDecimal.ZERO)
                                .build())
                        .priceType(PriceType.builder()
                           //     .id(productProperties.getPriceTypes().get(randomValue).getId())
                                .value(productProperties.getPriceTypes().get(randomValue).getValue())
                                .build())
                        .status(Status.builder()
                             //   .id(productProperties.getStatus().get(ID_IN_STOCK.intValue()).getId())
                                .statusType(productProperties.getStatus().get(ID_IN_STOCK.intValue()).getStatusType())
                                .build())
                        .price(BigDecimal.ZERO)
                        .stockCount(randomStock)
                        .iconUrl(iconUrl)
                        .build();

                if (book.getStockCount() == 0) {
                    book.setStatus(Status.builder()
                   //         .id(productProperties.getStatus().get(ID_UNAVAILABLE.intValue()).getId())
                            .statusType(productProperties.getStatus().get(ID_UNAVAILABLE.intValue()).getStatusType())
                            .build());
                }

                if (book.getPriceType().getValue().equals(VALUE_NOT_FREE)) {
                    book.setPrice(BigDecimal.valueOf(randomPrice));
                }
                books.add(book);
            }
        }
        AVAILABLE_BOOKS = books;

    }

    @Override
    public ResponseEntity<Set<Book>> getBooks() {
        return ResponseEntity.ok(AVAILABLE_BOOKS);
    }

    /*@Override
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
    }*/
}

package ro.nicolaemariusghergu.easylearn.datamanager.domain;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(
        exclude = {"category", "publishHouse", "discount", "status",
                "author", "priceType", "stockCount", "iconUrl"},
        callSuper = false
)
public class Book extends AbstractEntity {

    private String title;

    private Category category;

    private PublishHouse publishHouse;

    private Discount discount;

    private Status status;

    private Author author;

    private PriceType priceType;

    private Integer stockCount;

    private String iconUrl;

    private BigDecimal price;
}

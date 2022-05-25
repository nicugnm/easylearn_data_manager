package ro.nicolaemariusghergu.easylearn.datamanager.domain;

import lombok.*;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString
public class Book extends AbstractEntity {

    private Category category;

    private PublishHouse publishHouse;

    private Discount discount;

    private Status status;

    private Author author;

    private PriceType priceType;

    private Integer stockCount;

    private String iconUrl;
}

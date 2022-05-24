package ro.nicolaemariusghergu.easylearn.datamanager.domain;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString
public class Book extends AbstractEntity {

    private Category category;

    private Status status;

    private Discount discount;

    private PriceType priceType;

    private PublishHouse publishHouse;

    private Integer stockCount;

    private String iconUrl;
}

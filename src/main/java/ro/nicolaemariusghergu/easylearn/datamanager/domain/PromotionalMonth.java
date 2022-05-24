package ro.nicolaemariusghergu.easylearn.datamanager.domain;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Builder
public class PromotionalMonth extends AbstractEntity {

    private Discount discount;
}

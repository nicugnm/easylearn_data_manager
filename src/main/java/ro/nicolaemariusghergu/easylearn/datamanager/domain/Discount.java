package ro.nicolaemariusghergu.easylearn.datamanager.domain;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
@ToString
public class Discount extends AbstractEntity {

    private BigDecimal value;
}

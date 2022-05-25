package ro.nicolaemariusghergu.easylearn.datamanager.domain;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class Category extends AbstractEntity {

    private String name;
}

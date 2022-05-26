package ro.nicolaemariusghergu.easylearn.datamanager.domain;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = false)
@SuperBuilder
public class Category extends AbstractEntity {

    private String name;
}

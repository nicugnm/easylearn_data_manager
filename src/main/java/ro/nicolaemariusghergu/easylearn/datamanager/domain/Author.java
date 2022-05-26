package ro.nicolaemariusghergu.easylearn.datamanager.domain;

import lombok.*;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString
@EqualsAndHashCode(callSuper = false)
@Getter
@Setter
public class Author extends AbstractEntity {

    private String name;
}

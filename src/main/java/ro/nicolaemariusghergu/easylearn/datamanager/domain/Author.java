package ro.nicolaemariusghergu.easylearn.datamanager.domain;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode(callSuper = false)
@Getter
@Setter
public class Author extends AbstractEntity {

    private String name;
}

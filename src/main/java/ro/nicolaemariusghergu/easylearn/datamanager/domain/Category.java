package ro.nicolaemariusghergu.easylearn.datamanager.domain;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
@Builder
public class Category extends AbstractEntity {

    private String name;
}

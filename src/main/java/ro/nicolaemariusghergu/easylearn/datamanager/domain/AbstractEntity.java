package ro.nicolaemariusghergu.easylearn.datamanager.domain;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public abstract class AbstractEntity {

    protected Long id;
}

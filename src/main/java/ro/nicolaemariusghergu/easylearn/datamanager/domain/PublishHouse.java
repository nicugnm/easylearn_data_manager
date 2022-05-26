package ro.nicolaemariusghergu.easylearn.datamanager.domain;

import lombok.*;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
public class PublishHouse extends AbstractEntity {

    private String name;
}

package ro.nicolaemariusghergu.easylearn.datamanager.domain;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Status extends AbstractEntity {

    private String statusType;
}

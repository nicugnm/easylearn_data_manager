package ro.nicolaemariusghergu.easylearn.datamanager.service;

import org.springframework.http.ResponseEntity;
import ro.nicolaemariusghergu.easylearn.datamanager.domain.PublishHouse;

import java.util.Set;

public interface PublishHouseService {

    Set<PublishHouse> extractPublishHousesFromRobmiles(String url);

    ResponseEntity<Set<PublishHouse>> getAvailablePublishHouses();
}

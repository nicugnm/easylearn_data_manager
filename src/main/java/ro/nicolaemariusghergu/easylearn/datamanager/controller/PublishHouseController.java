package ro.nicolaemariusghergu.easylearn.datamanager.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.nicolaemariusghergu.easylearn.datamanager.domain.PublishHouse;
import ro.nicolaemariusghergu.easylearn.datamanager.service.PublishHouseService;

import java.util.Set;

@RestController
@RequestMapping("/api/publish-house")
public record PublishHouseController(PublishHouseService publishHouseService) {

    @GetMapping("/v1")
    public ResponseEntity<Set<PublishHouse>> getPublishHouses() {
        return publishHouseService.getAvailablePublishHouses();
    }
}

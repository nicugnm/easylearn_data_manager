package ro.nicolaemariusghergu.easylearn.datamanager.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.nicolaemariusghergu.easylearn.datamanager.domain.Status;
import ro.nicolaemariusghergu.easylearn.datamanager.service.StatusService;

import java.util.List;

@RestController
@RequestMapping("/api/status-types")
public record StatusController(StatusService statusService) {

    @GetMapping("/v1")
    public ResponseEntity<List<Status>> getStatuses() {
        return statusService.getAvailableStatuses();
    }
}

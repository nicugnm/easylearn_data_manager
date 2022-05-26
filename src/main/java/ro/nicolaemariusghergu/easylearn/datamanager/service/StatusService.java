package ro.nicolaemariusghergu.easylearn.datamanager.service;

import org.springframework.http.ResponseEntity;
import ro.nicolaemariusghergu.easylearn.datamanager.domain.Status;

import java.util.List;

public interface StatusService {

    ResponseEntity<List<Status>> getAvailableStatuses();
}

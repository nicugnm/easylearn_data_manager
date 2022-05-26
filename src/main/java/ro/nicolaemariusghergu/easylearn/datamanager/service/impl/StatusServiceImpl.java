package ro.nicolaemariusghergu.easylearn.datamanager.service.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ro.nicolaemariusghergu.easylearn.datamanager.config.ProductProperties;
import ro.nicolaemariusghergu.easylearn.datamanager.domain.Status;
import ro.nicolaemariusghergu.easylearn.datamanager.service.StatusService;

import java.util.List;

@Service
public record StatusServiceImpl(ProductProperties productProperties)
        implements StatusService {

    @Override
    public ResponseEntity<List<Status>> getAvailableStatuses() {
        return ResponseEntity.ok(productProperties.getStatus());
    }
}

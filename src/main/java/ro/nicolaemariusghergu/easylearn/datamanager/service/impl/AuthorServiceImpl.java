package ro.nicolaemariusghergu.easylearn.datamanager.service.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ro.nicolaemariusghergu.easylearn.datamanager.domain.Author;
import ro.nicolaemariusghergu.easylearn.datamanager.service.AuthorService;

import java.util.ArrayList;
import java.util.List;

@Service
public record AuthorServiceImpl()
        implements AuthorService {

    @Override
    public ResponseEntity<List<Author>> getAuthors() {
        return ResponseEntity.ok(new ArrayList<>());
    }
}

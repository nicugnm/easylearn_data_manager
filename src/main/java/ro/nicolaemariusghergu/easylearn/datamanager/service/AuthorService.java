package ro.nicolaemariusghergu.easylearn.datamanager.service;

import org.springframework.http.ResponseEntity;
import ro.nicolaemariusghergu.easylearn.datamanager.domain.Author;

import java.util.List;

public interface AuthorService {

    ResponseEntity<List<Author>> getAuthors();
}

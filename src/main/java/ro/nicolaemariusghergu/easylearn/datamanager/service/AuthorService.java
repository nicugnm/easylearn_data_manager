package ro.nicolaemariusghergu.easylearn.datamanager.service;

import org.springframework.http.ResponseEntity;
import ro.nicolaemariusghergu.easylearn.datamanager.domain.Author;

import java.util.Set;

public interface AuthorService {

    Set<Author> extractAuthorsFromRobmiles(String url);

    ResponseEntity<Set<Author>> getAuthors();
}

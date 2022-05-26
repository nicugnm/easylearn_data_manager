package ro.nicolaemariusghergu.easylearn.datamanager.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.nicolaemariusghergu.easylearn.datamanager.domain.Author;
import ro.nicolaemariusghergu.easylearn.datamanager.service.AuthorService;

import java.util.Set;

@RestController
@RequestMapping("/api/authors")
public record AuthorController(AuthorService authorService) {

    @GetMapping("/v1")
    public ResponseEntity<Set<Author>> getAuthorsFromInserts() {
        return authorService.getAuthors();
    }
}

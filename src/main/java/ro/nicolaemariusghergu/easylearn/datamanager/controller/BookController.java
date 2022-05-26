package ro.nicolaemariusghergu.easylearn.datamanager.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.nicolaemariusghergu.easylearn.datamanager.domain.Book;
import ro.nicolaemariusghergu.easylearn.datamanager.service.BookService;

import java.util.Set;

@RestController
@RequestMapping("/api/books")
public record BookController(BookService bookService) {

    @GetMapping("/v1")
    public ResponseEntity<Set<Book>> getAvailableBooks() {
        return bookService.getBooks();
    }
}

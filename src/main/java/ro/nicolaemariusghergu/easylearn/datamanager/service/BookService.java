package ro.nicolaemariusghergu.easylearn.datamanager.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ro.nicolaemariusghergu.easylearn.datamanager.domain.Author;
import ro.nicolaemariusghergu.easylearn.datamanager.domain.Book;
import ro.nicolaemariusghergu.easylearn.datamanager.domain.Category;

import java.util.Set;

@Service
public interface BookService {

    void extractBooksFromRobmiles(String url, Set<Category> categories, Set<Author> authors);

    ResponseEntity<Set<Book>> getBooks();
}

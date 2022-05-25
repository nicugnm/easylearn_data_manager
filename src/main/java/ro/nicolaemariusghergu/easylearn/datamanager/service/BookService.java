package ro.nicolaemariusghergu.easylearn.datamanager.service;

import org.springframework.stereotype.Service;
import ro.nicolaemariusghergu.easylearn.datamanager.domain.Author;
import ro.nicolaemariusghergu.easylearn.datamanager.domain.Book;
import ro.nicolaemariusghergu.easylearn.datamanager.domain.Category;

import java.util.List;
import java.util.Set;

@Service
public interface BookService {

    List<Book> extractBooksFromRobmiles(String url, List<Category> categories, List<Author> authors);

    void createInserts(List<Book> books);

    Set<String> getInserts();
}

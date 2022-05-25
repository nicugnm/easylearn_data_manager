package ro.nicolaemariusghergu.easylearn.datamanager.service;

import ro.nicolaemariusghergu.easylearn.datamanager.domain.Author;

import java.util.List;
import java.util.Set;

public interface AuthorService {

    List<Author> extractAuthorsFromRobmiles(String url);

    void createInserts(List<Author> authors);

    Set<String> getInserts();
}

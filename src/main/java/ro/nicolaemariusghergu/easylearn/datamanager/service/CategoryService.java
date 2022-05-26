package ro.nicolaemariusghergu.easylearn.datamanager.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ro.nicolaemariusghergu.easylearn.datamanager.domain.Category;

import java.util.Set;

@Service
public interface CategoryService {

    Set<Category> extractCategoriesFromRobmiles(String url);

    ResponseEntity<Set<Category>> getCategories();
}

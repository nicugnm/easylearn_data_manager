package ro.nicolaemariusghergu.easylearn.datamanager.service;

import org.springframework.stereotype.Service;
import ro.nicolaemariusghergu.easylearn.datamanager.domain.Category;

import java.util.List;
import java.util.Set;

@Service
public interface CategoryService {

    List<Category> extractCategoriesFromRobmiles(String url);

    void createInserts(List<Category> categories);

    Set<String> getInserts();
}

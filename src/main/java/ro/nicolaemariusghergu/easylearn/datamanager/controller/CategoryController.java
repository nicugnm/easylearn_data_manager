package ro.nicolaemariusghergu.easylearn.datamanager.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.nicolaemariusghergu.easylearn.datamanager.service.CategoryService;

import java.util.Set;

@RestController
@RequestMapping("/api/categories")
public record CategoryController(CategoryService categoryService) {

    @GetMapping("/v1/inserts")
    public ResponseEntity<Set<String>> getCategoryInserts() {
        return ResponseEntity.ok(categoryService.getInserts());
    }
}

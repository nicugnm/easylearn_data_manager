package ro.nicolaemariusghergu.easylearn.datamanager.schedules;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import ro.nicolaemariusghergu.easylearn.datamanager.config.UrlPropertiesPath;
import ro.nicolaemariusghergu.easylearn.datamanager.service.AuthorService;
import ro.nicolaemariusghergu.easylearn.datamanager.service.BookService;
import ro.nicolaemariusghergu.easylearn.datamanager.service.CategoryService;

@Slf4j
@Configuration
@EnableScheduling
@RequiredArgsConstructor
public class UpdateDataScheduler {

    private final CategoryService categoryService;
    private final AuthorService authorService;
    private final BookService bookService;
    private final UrlPropertiesPath urlPropertiesPath;

    @Scheduled(fixedRate = 1000 * 60 * 60) // 1 hour
    public void run() {
        log.info("Starting saving informations about Categories...");
        var categories = categoryService.extractCategoriesFromRobmiles(urlPropertiesPath.getUrl());

        log.info("Starting saving informations about Authors...");
        var authors = authorService.extractAuthorsFromRobmiles(urlPropertiesPath.getUrl());

        log.info("Starting saving informations about Books...");
        bookService.extractBooksFromRobmiles(urlPropertiesPath.getUrl(), categories, authors);
    }
}

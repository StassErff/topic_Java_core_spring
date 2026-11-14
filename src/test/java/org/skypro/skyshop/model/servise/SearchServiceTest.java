package org.skypro.skyshop.model.servise;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.search.SearchResult;
import org.skypro.skyshop.model.search.Searchable;
import org.skypro.skyshop.model.service.BasketService;
import org.skypro.skyshop.model.service.SearchService;
import org.skypro.skyshop.model.service.StorageService;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SearchServiceTest {
    @Mock
    private StorageService storageService;

    @InjectMocks
    private SearchService searchService;

    @BeforeEach
    void setUp() {
        searchService = new SearchService(storageService);
    }

    @Test
    public void searchWhenEmptyList() {
        when(storageService.getAllSearchable()).thenReturn(Collections.emptyList());

        Collection<SearchResult> results = searchService.search("колбаса");

        assertThat(results).isEmpty();

    }

    @Test
    public void searchWhenNoSuitableObjectFound() {
        when(storageService.getAllSearchable()).thenReturn(createTestItems());

        Collection<SearchResult> results = searchService.search("газировка");

        assertThat(results).isEmpty();
    }

    private List<Searchable> createTestItems() {
        return List.of(
                new SimpleProduct(UUID.randomUUID(), "хлеб", 50),
                new SimpleProduct(UUID.randomUUID(), "молоко", 80),
                new SimpleProduct(UUID.randomUUID(), "колбаса докторская", 350),
                new Article(UUID.randomUUID(), "рецепт бутерброда", "возьмите хлеб и сыр")
        );
    }

    @Test
    public void searchWhenSuitableObjectExists() {
        SimpleProduct expectedProduct = new SimpleProduct(UUID.randomUUID(), "колбаса докторская", 350);
        List<Searchable> items = List.of(
                new SimpleProduct(UUID.randomUUID(), "хлеб", 50),
                expectedProduct,
                new Article(UUID.randomUUID(), "рецепт бутерброда", "возьмите хлеб и сыр")
        );
        when(storageService.getAllSearchable()).thenReturn(items);

        Collection<SearchResult> results = searchService.search("колбаса");

        assertThat(results).hasSize(1);
        assertThat(results)
                .extracting(SearchResult::getNameSearch)
                .containsExactly("колбаса докторская");
    }
}

package org.skypro.skyshop.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.model.product.FixPriceProduct;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.search.SearchResult;

import java.util.*;

import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class SearchServiceTest {
    @Mock
    private StorageService storageService;

    @InjectMocks
    private SearchService searchService;

    @BeforeEach
    void setUp() {
        // Код, который выполнится ПЕРЕД каждым тестом
        searchService = new SearchService(storageService);
    }

    @Test
    public void searchWhenEmptyList() {
        //возвращаем пустой список
        when(storageService.getAllCollection()).thenReturn(Collections.emptyList());
        // вызываем метод поиска
        Collection<SearchResult> results = searchService.search("яблоки");
        // проверяем, что результат пуст
        assertThat(results).isEmpty();
    }

    @Test
    public void searchDoesntMatch() {
        // возвращаем список с объектами, которые НЕ содержат слово "яблоки"
        when(storageService.getAllCollection()).thenReturn(Arrays.asList(
                new SimpleProduct("банан", 100, UUID.randomUUID()),
                new FixPriceProduct("печенье", UUID.randomUUID())));
        // вызываем метод поиска
        Collection<SearchResult> results = searchService.search("яблоки");
        // проверяем, что результат пуст
        assertThat(results).isEmpty();
    }


    @Test
    public void searchCoincidence() {
        // возвращаем список, где есть TestProduct
        when(storageService.getAllCollection()).thenReturn(Arrays.asList(
                new SimpleProduct("TestProduct", 100, UUID.randomUUID()),
                new FixPriceProduct("OtherProduct", UUID.randomUUID())
        ));
        // вызываем метод поиска с pattern = "Test"
        Collection<SearchResult> results = searchService.search("Test");
        // список не пуст
        assertThat(results).isNotEmpty();
        // Ровно один товар. OtherProduct отсеяли правильно
        assertThat(results).hasSize(1);
        // Проверяем, что найденный объект действительно называется "TestProduct"
        // extracting заглядывает внутрь каждого объекта в коллекции results,
        // находит у него поле (или геттер) с именем name и создает новую коллекцию, состоящую только из этих названий
        assertThat(results).extracting("name").contains("TestProduct");
    }
}

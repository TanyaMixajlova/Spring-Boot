package org.skypro.skyshop.service;
import org.skypro.skyshop.model.search.Searchable;
import org.skypro.skyshop.model.search.SearchResult;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class SearchService {
    private final StorageService storageService;

    public SearchService(StorageService storageService) {
        this.storageService = storageService;
    }
    public Collection<SearchResult> search(String element) {
        return storageService.getAllCollection().stream()
                //.filter(searchable -> searchable.getName().contains(element)) // фильтруем по строке поиска
                .filter(searchable -> {
                    boolean match = searchable.getName().contains(element);
                    System.out.println("Checking " + searchable.getName() + ": " + match);
                    return match;
                })
                .map(SearchResult::fromSearchable) // преобразуем в SearchResult
                .collect(Collectors.toList()); // собираем в список
    }
}

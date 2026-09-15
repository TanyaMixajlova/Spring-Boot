package org.skypro.skyshop.service;

import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.DiscountedProduct;
import org.skypro.skyshop.model.product.FixPriceProduct;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.search.Searchable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class StorageService {
    private final Map<UUID, Product> mapProduct = new HashMap<>();
    private final Map<UUID, Article> mapArticle = new HashMap<>();

    public StorageService() {
        addingProducts();
        addingArticles();
    }
    public Collection<Article> getAllArticles() {
        return mapArticle.values();
    }
    public Collection<Product> getAllProducts() {
        return mapProduct.values();
    }

    public void addingProducts() {
        Product redApples = new SimpleProduct("яблоки красные", 150, UUID.randomUUID());
        Product greenApples = new DiscountedProduct("яблоки зеленые", 200, (byte) 20, UUID.randomUUID());
        Product milk = new FixPriceProduct("молоко", UUID.randomUUID());

        mapProduct.put(redApples.getId(), redApples);
        mapProduct.put(greenApples.getId(), greenApples);
        mapProduct.put(milk.getId(), milk);
    }

    public void addingArticles() {
        mapArticle.put(UUID.randomUUID(), new Article("Питон язык программирования и Java язык программирования ", "Полиморфиз и интерфейсы", UUID.randomUUID()));
        mapArticle.put(UUID.randomUUID(), new Article("си язык программирования ", "Текст статьи 2", UUID.randomUUID()));
        mapArticle.put(UUID.randomUUID(), new Article("си++ язык программирования ", "Текст статьи 3", UUID.randomUUID()));
    }

    public Collection<Searchable> getAllCollection() {
        List<Searchable> searchables = new ArrayList<>();
        searchables.addAll(mapProduct.values()); // productMap - это Map с продуктами
        searchables.addAll(mapArticle.values()); // articleMap - это Map со статьями
        return searchables;
    }

    public Optional<Product> getProductById(UUID id) {
        return Optional.ofNullable(mapProduct.get(id));
    }

}

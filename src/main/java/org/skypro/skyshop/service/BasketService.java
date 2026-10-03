package org.skypro.skyshop.service;

import org.skypro.skyshop.model.basket.BasketItem;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.model.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BasketService {
    private final ProductBasket productBasket;
    private final StorageService storageService;

    // Зависимость внедряется через конструктор
    @Autowired
    public BasketService(ProductBasket productBasket, StorageService storageService) {
        this.productBasket = productBasket;
        this.storageService = storageService;
    }

    //добавляем продукт
    public void addSearchProduct(UUID id) {
        storageService.getProductById(id);
        Product product = (storageService.getProductById(id)).orElseThrow(() -> new IllegalArgumentException("Продукт не найден"));
        productBasket.addProduct(id);
    }

    //public List<BasketItem> getBasketItems() {
    //    return productBasket.getBasketStream()
    //            .map(entry -> {
    //               Product product = storageService.getProductById(entry.getKey())
     //                       .orElseThrow(() -> new IllegalArgumentException("Product not found"));
     //               return new BasketItem(product, entry.getValue());
    //            })
    //            .collect(Collectors.toList());
   // }

    public UserBasket getUserBasket() {
        Map<UUID, Integer> contents = productBasket.getProductContents();
        List<BasketItem> items = contents.entrySet().stream()
                .map(entry -> new BasketItem(storageService.getProductById(entry.getKey()).orElseThrow(), entry.getValue()))
                .collect(Collectors.toList());
        return new UserBasket(items);
    }
}

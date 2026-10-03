package org.skypro.skyshop.model.basket;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.*;
import java.util.stream.Stream;

@Component
@SessionScope
public class ProductBasket {
    private final Map<UUID, Integer> basket = new HashMap<>();

    //добавляем продукт
    public void addProduct(UUID id) {
        basket.computeIfAbsent(id, k -> {
            int currentValue = basket.getOrDefault(k, 0);
            return currentValue + 1;
        });
    }
    //получение содержимого корзины
    public Map<UUID, Integer>  getProductContents(){
        return Collections.unmodifiableMap(basket);
    }
    //public Stream<Map.Entry<UUID, Integer>> getBasketStream() {
    //    return basket.entrySet().stream();
   // }
}

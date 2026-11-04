package org.skypro.skyshop.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.search.SearchResult;
import java.util.Collections;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BasketServiceTest {

    //Создаем поддельный Склад
    @Mock
    private StorageService storageService;

    //Создаем поддельную Корзину (хранилище товаров пользователя)
    @Mock
    private ProductBasket productBasket;

    //Создаем РЕАЛЬНЫЙ сервис корзины, и Mockito сам передаст в него два мока выше
    @InjectMocks
    private BasketService basketService;

    //добавление в корзину несуществующего товара StorageService
    @Test
    public void addNonExistent() {
        // Создаем ID несуществующего товара
        UUID invalidId = UUID.randomUUID();
        // возвращаем пустой Optional
        when(storageService.getProductById(invalidId)).thenReturn(Optional.empty());
        // Проверяем, что при вызове метода выбрасывается нужное исключение
        assertThrows(IllegalArgumentException.class, () -> basketService.addSearchProduct(invalidId));
    }

    @Test
    public void addExistingProduct() {
        //Создаем ID существующего товара
        UUID validId = UUID.randomUUID();
        // Создаем реальный продукт
        Product product = new SimpleProduct("яблоки", 100, validId);
        // Говорим моку: если спросят этот ID, верни продукт
        when(storageService.getProductById(validId)).thenReturn(Optional.of(product));
        //Вызываем метод добавления товара
        basketService.addSearchProduct(validId);
        //Проверяем, что у мока productBasket метод addProduct был вызван ровно 1 раз с этим ID
        verify(productBasket, times(1)).addProduct(validId);
    }
    @Test
    public void getEmptyBasket() {
        // Говорим моку: верни пустую мапу
        when(productBasket.getProductContents()).thenReturn(Collections.emptyMap());
        // Вызываем метод получения корзины
        UserBasket userBasket = basketService.getUserBasket();
        // Проверяем, что корзина пустая
        assertThat(userBasket.getBasketWithTotal()).isEmpty();
        assertThat(userBasket.getTotal()).isZero();
    }

    @Test
    public void getBasketWithProducts() {
        // Создаем ID товара и количество
        UUID productId = UUID.randomUUID();
        int quantity = 2;
        // Создаем МАПУ: id товара → количество
        Map<UUID, Integer> basketMap = new HashMap<>();
        basketMap.put(productId, quantity);
        // Говорим моку productBasket: верни эту мапу
        when(productBasket.getProductContents()).thenReturn(basketMap);
        // Создаем продукт с известной ценой (100 рублей)
        int price = 100;
        Product product = new SimpleProduct("яблоки", price, productId);
        // Говорим моку storageService: верни этот продукт по ID
        when(storageService.getProductById(productId)).thenReturn(Optional.of(product));
        // Вызываем метод получения корзины
        UserBasket userBasket = basketService.getUserBasket();
        // Проверяем результат
        // В корзине должен быть 1 товар
        assertThat(userBasket.getBasketWithTotal()).hasSize(1);
        // Общая сумма = цена × количество = 100 × 2 = 200
        assertThat(userBasket.getTotal()).isEqualTo(200);
    }
}



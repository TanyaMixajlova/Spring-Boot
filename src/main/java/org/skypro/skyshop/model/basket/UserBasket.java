package org.skypro.skyshop.model.basket;
import java.util.List;

public final class UserBasket {
    private final List<BasketItem> basketWithTotal;
    private int total;

    public UserBasket(List<BasketItem> basketWithTotal) {
        this.basketWithTotal = List.copyOf(basketWithTotal);
        this.total = basketWithTotal.stream()
                .mapToInt(item -> item.getProduct().getPrice() * item.getQuantity())
                .sum();
    }
    public List<BasketItem> getBasketWithTotal() {
        return basketWithTotal;
    }

    public double getTotal() {
        return total;
    }
}

package org.skypro.skyshop.model.product;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.UUID;

public class DiscountedProduct extends Product {
    private final int basePrice;
    private final byte discount;


    public DiscountedProduct(String name, int basePrice, byte discount, UUID id) {
        super(name, id);
        this.basePrice = basePrice;
        if (this.basePrice <= 0) {
            throw new IllegalArgumentException("Цена должна быть больше нуля");
        }
        this.discount = discount;
        if (this.discount < 0 || this.discount >100) {
            throw new IllegalArgumentException("Процент должен быть в пределах 100");
        }
    }

    @Override
    public int getPrice() {
        int discountAmount = basePrice*discount/100;
        return basePrice-discountAmount;
    }

    @Override
    public String toString() {
        return "Имя продукта со скидкой " + super.toString() + ": "+ getPrice() + " (" + this.discount+ ")";
    }

    @Override
    public boolean isSpecial() {
        return true;
    }
    @JsonIgnore
    @Override
    public String getSearchTerm() {
        return "";
    }
    @JsonIgnore
    @Override
    public String getContentType() {
        return "";
    }
}

package org.skypro.skyshop.model.product;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.UUID;

public class SimpleProduct extends Product {
    private final int price;

    public SimpleProduct(String name, int price, UUID id) {
        super(name, id);
        this.price = price;
        if (this.price <= 0) {
            throw new IllegalArgumentException("Цена должна быть больше нуля");
        }
    }

    @Override
    public int getPrice() {
        return this.price;
    }


    @Override
    public String toString() {
        return super.toString() + ": " + this.price;
    }

    @Override
    public boolean isSpecial() {
        return false;
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

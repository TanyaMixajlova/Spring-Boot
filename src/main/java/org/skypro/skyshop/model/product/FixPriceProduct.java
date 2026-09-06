package org.skypro.skyshop.model.product;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.UUID;

public class FixPriceProduct extends Product{
    private static final int FIXED_PRICE=100;
    private int price;

    public FixPriceProduct (String name, UUID id){
        super(name, id);
        this.price = FIXED_PRICE;
    }
    @Override
    public int getPrice() {
        return FIXED_PRICE;
    }

    @Override
    public String toString() {
        return super.toString() + ": Фиксированная цена " + FIXED_PRICE;
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


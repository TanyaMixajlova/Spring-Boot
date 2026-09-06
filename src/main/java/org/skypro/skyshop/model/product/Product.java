package org.skypro.skyshop.model.product;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.skypro.skyshop.model.search.Searchable;

import java.util.Objects;
import java.util.UUID;

public abstract class Product implements Searchable {
    private final String name;
    private final UUID id;

    public Product(String name, UUID id) {
        this.name = name;
        this.id = id;
        if ((this.name == null || this.name.isBlank())){
            throw new IllegalArgumentException("Имя не может быть создано");
        }
    }
    @Override
    public UUID getId(){return this.id; }

    public String getName() {
        return this.name;
    }
    public abstract int getPrice();

    @Override
    public String toString() {
        return this.name;
    }


    public abstract boolean isSpecial();
    @JsonIgnore
    @Override
    public String searchTerm(){
        return name;
    };
    @JsonIgnore
    @Override
    public String contentType() {
        return "PRODUCT";
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        Product product = (Product) other;
        return Objects.equals(name, product.name);
    }
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}


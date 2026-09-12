package org.skypro.skyshop.model.search;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.UUID;

public interface Searchable {
    String getName();
    String getSearchTerm();
    @JsonIgnore
    String getContentType();
    default String getStringRepresentation() {
        return getSearchTerm() + " - " + getContentType();
    }
    UUID getId();

}

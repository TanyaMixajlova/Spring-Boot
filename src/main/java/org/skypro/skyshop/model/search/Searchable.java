package org.skypro.skyshop.model.search;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.UUID;

public interface Searchable {
    String getName();
    String searchTerm();
    String contentType();
    default String getStringRepresentation() {
        return searchTerm() + " - " + contentType();
    }
    UUID getId();

    String getSearchTerm();

    @JsonIgnore
    String getContentType();
}

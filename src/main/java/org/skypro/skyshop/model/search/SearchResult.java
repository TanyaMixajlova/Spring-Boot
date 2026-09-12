package org.skypro.skyshop.model.search;

import org.springframework.web.bind.annotation.GetMapping;

public class SearchResult {
    private final String id;
    private final String name;
    private final String contentType;

    public SearchResult(String id, String name, String contentType) {
        this.id = id;
        this.name = name;
        this.contentType = contentType;
    }

    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getContentType() {
        return contentType;
    }

    @Override
    public String toString() {
        return "id: " + id + ", name: " + name + ", contentType: " + contentType;
    }
    public static SearchResult fromSearchable(Searchable searchables) {
        return new SearchResult(searchables.getId().toString(), searchables.getName(), searchables.getContentType());
    }

}

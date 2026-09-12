package org.skypro.skyshop.model.article;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.skypro.skyshop.model.search.Searchable;

import java.util.Objects;
import java.util.UUID;

public class Article implements Searchable {
    @JsonIgnore
    private final String titleArticle;
    @JsonIgnore
    private final String textArticle;
    private final UUID id;

    public Article(String titleArticle, String textArticle, UUID id) {
        this.titleArticle = titleArticle;
        this.textArticle = textArticle;
        this.id= id;
    }
    @Override
    public UUID getId(){return this.id; }

        @Override
    public String toString() {
        return this.titleArticle + "\n" + this.textArticle;
    }

    public String getTitleArticle() {
        return this.titleArticle;
    }
    public String getTextArticle() {
        return this.textArticle;
    }

    @Override
    public String getName() {
        return titleArticle;
    }

    @JsonIgnore
    @Override
    public String getSearchTerm(){
        return titleArticle + textArticle;
    };


    @Override
    @JsonIgnore
    public String getContentType() {
        return "ARTICLE";
    }

    @Override
    public String getStringRepresentation() {
        return Searchable.super.getStringRepresentation();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return Objects.equals(titleArticle, article.titleArticle);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(titleArticle);
    }

}

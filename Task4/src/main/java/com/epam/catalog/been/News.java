package com.epam.catalog.been;

import java.io.Serializable;

/**
 * Created by Yauheni_Tsitsenkou on 2/1/2017.
 */
public class News implements Serializable {
    private Category category;
    private String title;
    private String author;

    public News(Category category, String title, String author) {
        this.category = category;
        this.title = title;
        this.author = author;
    }

    public News() {

    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        News news = (News) obj;

        return (category == null ? news.category == null : category.equals(news.category)) &&
                (title == null ? news.title == null : title.equals(news.title)) &&
                (author == null ? news.author == null : author.equals(news.author));
    }

    public int hashCode() {
        int result = category != null ? category.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (author != null ? author.hashCode() : 0);
        return result;
    }

    public String toString() {
        return "News{" +
                "category=" + category +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}

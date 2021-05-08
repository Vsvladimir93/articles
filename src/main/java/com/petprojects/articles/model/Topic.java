package com.petprojects.articles.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
public class Topic extends EntitySuperclass {

    @NotBlank(message = "REQUIRED")
    @Column(unique = true)
    private String title;

    @OneToMany(mappedBy = "topic")
    @JsonManagedReference
    @JsonIgnoreProperties(value = {"body"})
    private Set<Article> articles;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Article> getArticles() {
        return articles;
    }

    public void setArticles(Set<Article> articles) {
        this.articles = articles;
    }

    public Topic() {
    }

    public Topic(long id, String title, Set<Article> articles) {
        setId(id);
        this.title = title;
        this.articles = articles;
    }

}

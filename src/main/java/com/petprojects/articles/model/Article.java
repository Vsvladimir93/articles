package com.petprojects.articles.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Article extends EntitySuperclass {

    @NotNull
    @ManyToOne
    @JsonBackReference
    private Topic topic;

    @NotBlank
    private String title;

    @NotBlank
    private String body;

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}

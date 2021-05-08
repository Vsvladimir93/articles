package com.petprojects.articles.controller;

import com.petprojects.articles.model.Article;
import com.petprojects.articles.service.ArticleService;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@RestController
public class ArticleController {

    private final ArticleService service;
    private final Logger logger;

    public ArticleController(ArticleService service, Logger logger) {
        this.service = service;
        this.logger = logger;
    }

    @PostMapping("topic/{topicId}/article")
    public ResponseEntity<Article> create(@PathVariable Long topicId, @RequestBody Article article) {
        return service.create(topicId, article)
                .map(value -> new ResponseEntity<Article>(value, HttpStatus.CREATED))
                .orElseGet(() -> new ResponseEntity<Article>(HttpStatus.BAD_REQUEST));
    }

    @GetMapping("topic/{topicId}/article")
    public ResponseEntity<Set<Article>> findAll(@PathVariable Long topicId) {
        Optional<Set<Article>> result = service.findAll(topicId);

        return result.map(articles -> new ResponseEntity<Set<Article>>(articles, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<Set<Article>>(HttpStatus.BAD_REQUEST));
    }

    @GetMapping("article/{id}")
    public ResponseEntity<Article> findById(@PathVariable Long id) {
        Optional<Article> result = service.findById(id);

        return result.map(article -> new ResponseEntity<Article>(article, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<Article>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("article/{id}")
    public ResponseEntity<Article> update(@PathVariable Long id, @RequestBody Article article) {
        Optional<Article> result = service.update(id, article);

        return result.map(value -> new ResponseEntity<Article>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<Article>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("article/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return new ResponseEntity<Void>(service.delete(id) ? HttpStatus.NO_CONTENT : HttpStatus.NOT_FOUND);
    }

}

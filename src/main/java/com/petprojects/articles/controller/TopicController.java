package com.petprojects.articles.controller;

import com.petprojects.articles.model.Topic;
import com.petprojects.articles.service.TopicService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("topic")
public class TopicController {

    private final TopicService service;

    public TopicController(TopicService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Topic> create(@Valid @RequestBody Topic topic) {
        // TODO Validate body, ignore id
        // TODO Return created topic
        return new ResponseEntity(service.create(topic), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Topic>> findAll() {
        return new ResponseEntity<List<Topic>>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Topic> findById(@PathVariable Long id) {
        Optional<Topic> result = service.findById(id);
        if (result.isPresent()) {
            return new ResponseEntity(result.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody Topic topic) {
        // TODO Validate body with id
        // TODO Return updated topic
        Optional<Topic> result = service.update(id, topic);
        if (result.isPresent()) {
            return new ResponseEntity(result.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return new ResponseEntity<Void>(service.delete(id) ? HttpStatus.NO_CONTENT : HttpStatus.NOT_FOUND);
    }

}

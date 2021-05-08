package com.petprojects.articles.service;

import com.petprojects.articles.repository.TopicRepository;
import com.petprojects.articles.model.Topic;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TopicService {

    private TopicRepository repository;

    public TopicService(TopicRepository repository) {
        this.repository = repository;
    }

    public Topic create(Topic topic) {
        return repository.save(topic);
    }

    public List<Topic> findAll() {
        return repository.findAll();
    }

    public Optional<Topic> findById(Long id) {
        return repository.findById(id);
    }

    public Optional<Topic> update(Long id, Topic topic) {
        Optional<Topic> result = repository.findById(id);

        if (result.isPresent()) {
            Topic topicToUpdate = result.get();
            topicToUpdate.setTitle(topic.getTitle());
            return Optional.of(repository.save(topicToUpdate));
        }

        return Optional.empty();
    }

    public boolean delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }

        return false;
    }

}

package com.petprojects.articles.service;

import com.petprojects.articles.model.Topic;
import com.petprojects.articles.repository.ArticleRepository;
import com.petprojects.articles.model.Article;
import com.petprojects.articles.repository.TopicRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class ArticleService {

    private final ArticleRepository articlesRepository;
    private final TopicRepository topicRepository;

    public ArticleService(ArticleRepository articlesRepository, TopicRepository topicRepository) {
        this.articlesRepository = articlesRepository;
        this.topicRepository = topicRepository;
    }

    public Optional<Article> create(Long topicId, Article article) {
        Optional<Topic> topic = topicRepository.findById(topicId);

        if (topic.isPresent()) {
            article.setTopic(topic.get());
            return Optional.of(articlesRepository.save(article));
        }

        return Optional.empty();
    }

    public Optional<Set<Article>> findAll(Long topicId) {
        Optional<Topic> topic = topicRepository.findById(topicId);

        return topic.map(Topic::getArticles);
    }

    public Optional<Article> findById(Long id) {
        return articlesRepository.findById(id);
    }

    public Optional<Article> update(Long id, Article article) {
        Optional<Article> result = articlesRepository.findById(id);

        if (result.isPresent()) {
            Article toUpdate = result.get();
            toUpdate.setTitle(article.getTitle());
            toUpdate.setBody(article.getBody());
            return Optional.of(articlesRepository.save(toUpdate));
        }

        return Optional.empty();
    }

    public boolean delete(Long id) {
        if (articlesRepository.existsById(id)) {
            articlesRepository.deleteById(id);
            return true;
        }

        return false;
    }
}

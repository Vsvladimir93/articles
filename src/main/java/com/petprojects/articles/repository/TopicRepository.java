package com.petprojects.articles.repository;

import com.petprojects.articles.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TopicRepository extends JpaRepository<Topic, Long> {

    @Override
    List<Topic> findAll();

}

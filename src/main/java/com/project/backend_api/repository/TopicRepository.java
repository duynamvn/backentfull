package com.project.backend_api.repository;

import com.project.backend_api.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.backend_api.model.Topic;

import java.util.List;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long>{

    @Query("SELECT c FROM Topic c ORDER BY c.id DESC")
    List<Topic> findTopTopicOrderByIdDesc();
}

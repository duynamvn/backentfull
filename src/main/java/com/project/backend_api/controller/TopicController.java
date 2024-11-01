package com.project.backend_api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.backend_api.model.Topic;
import com.project.backend_api.service.ITopicService;

@RestController
@RequestMapping("/api/topics")
public class TopicController {

    @Autowired
    private ITopicService iTopicService;

    @GetMapping
    public List<Topic> getAllTopic(){
        return iTopicService.getAllTopics();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Topic> getTopicById(@PathVariable Long id){
        Topic topics = iTopicService.getTopicById(id).orElse(null);
        return ResponseEntity.ok(topics);
    }

    @PostMapping
    public ResponseEntity<Topic> createTopic(@RequestBody Topic topic){
        Topic cretaeTopics = iTopicService.createTopic(topic);
        return ResponseEntity.ok(cretaeTopics);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Topic> updateTopic(@PathVariable Long id, @RequestBody Topic topic){
        Topic updateTopics = iTopicService.updateTopic(id, topic);
        return ResponseEntity.ok(updateTopics);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Topic> deleteTopic(@PathVariable Long id){
        iTopicService.deleteTopic(id);
        return ResponseEntity.noContent().build();
    }
}

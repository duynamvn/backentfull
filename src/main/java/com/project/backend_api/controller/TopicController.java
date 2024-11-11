package com.project.backend_api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<?> createTopic(@RequestBody Topic topic){
        try {
            String genarateTopicCode = generateTopicCode(topic.getTopicName());
            topic.setTopicCode(genarateTopicCode);
            Topic existingTopic = iTopicService.createTopic(topic);
            return ResponseEntity.status(HttpStatus.CREATED).body(existingTopic);
        } catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred" + e.getMessage());
        }
    }

    private String generateTopicCode(String topicName){
        if (topicName == null || topicName.trim().isEmpty()){
            throw new IllegalArgumentException("Topic name cannot be null or empty");
        }

        String[] words = topicName.split(" ");
        StringBuilder sb = new StringBuilder();
        for (String word : words){
            sb.append(word.charAt(0));
        }
        Long latestId = iTopicService.getLatestTopicId();
        int newId = latestId != null ? (int) (latestId + 1) : 1;

        return sb.toString().toUpperCase() + String.format("%02d", newId) ;
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTopic(@PathVariable Long id, @RequestBody Topic topic){
        try {
            Topic existingTopic = iTopicService.getTopicById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Topic not found with id: " + id));
            if (!existingTopic.getTopicName().equals(topic.getTopicName())){
                String newTopicCode = generateTopicCode(topic.getTopicName());
                topic.setTopicCode(newTopicCode);
            } else {
                topic.setTopicCode(existingTopic.getTopicCode());
            }
            topic.setId(existingTopic.getId());
            Topic updateTopic = iTopicService.updateTopic(id, topic);
            return ResponseEntity.ok(updateTopic);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred" + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Topic> deleteTopic(@PathVariable Long id){
        iTopicService.deleteTopic(id);
        return ResponseEntity.noContent().build();
    }
}

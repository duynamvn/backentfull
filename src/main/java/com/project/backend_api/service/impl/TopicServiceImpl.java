package com.project.backend_api.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.backend_api.model.Topic;
import com.project.backend_api.repository.TopicRepository;
import com.project.backend_api.service.ITopicService;

@Service
public class TopicServiceImpl implements ITopicService{

	@Autowired
    private TopicRepository topicRepository;

    @Override
    public List<Topic> getAllTopics() {
        return topicRepository.findAll();
    }

    @Override
    public Optional<Topic> getTopicById(Long id) {
        return topicRepository.findById(id);
    }

    @Override
    public Topic createTopic(Topic topic) {
        return topicRepository.save(topic);
    }

    @Override
    public Topic updateTopic(Long id, Topic topics) {
        Topic existingTopics = topicRepository.findById(id).orElse(null);
        if (existingTopics != null){
            existingTopics.setTopicCode(topics.getTopicCode());
            existingTopics.setTopicName(topics.getTopicName());
            existingTopics.setTheoryHours(topics.getTheoryHours());
            existingTopics.setPracticalHours(topics.getPracticalHours());
            existingTopics.setActivate(topics.getActivate());
            existingTopics.setOriginalPrice(topics.getOriginalPrice());
            existingTopics.setPromotionalPrice(topics.getPromotionalPrice());

            return topicRepository.save(existingTopics);
        }
        return null;
    }

    @Override
    public void deleteTopic(Long id) {
        topicRepository.deleteById(id);
    }

}

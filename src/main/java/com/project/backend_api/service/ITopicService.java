package com.project.backend_api.service;

import java.util.List;
import java.util.Optional;

import com.project.backend_api.model.Topic;

public interface ITopicService {

	List<Topic> getAllTopics();

	Optional<Topic> getTopicById(Long id);

	Topic createTopic(Topic topic);

	Topic updateTopic(Long id, Topic topic);

	void deleteTopic(Long id);
}

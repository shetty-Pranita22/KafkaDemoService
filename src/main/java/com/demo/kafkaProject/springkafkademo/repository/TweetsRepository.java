package com.demo.kafkaProject.springkafkademo.repository;

import com.demo.kafkaProject.springkafkademo.model.Tweet;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TweetsRepository extends MongoRepository<Tweet, Long> {
}

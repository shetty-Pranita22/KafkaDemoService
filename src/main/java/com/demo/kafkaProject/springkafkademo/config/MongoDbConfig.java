package com.demo.kafkaProject.springkafkademo.config;

import com.demo.kafkaProject.springkafkademo.repository.TweetsRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories(basePackageClasses = TweetsRepository.class)
@Configuration
public class MongoDbConfig {
}

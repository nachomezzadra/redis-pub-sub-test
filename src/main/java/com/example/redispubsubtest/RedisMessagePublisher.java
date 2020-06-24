package com.example.redispubsubtest;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;

/**
 * @autor Nacho Mezzadra
 */
@Service
public class RedisMessagePublisher {

  private final RedisTemplate<String, String> redisTemplate;
  private final ChannelTopic topic;

  public RedisMessagePublisher(
      RedisTemplate<String, String> redisTemplate,
      ChannelTopic topic) {
    this.redisTemplate = redisTemplate;
    this.topic = topic;
  }

  public void publish(String message) {
    redisTemplate.convertAndSend(topic.getTopic(), message);
  }
}

package com.example.redispubsubtest;

import org.redisson.spring.data.connection.RedissonConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

/**
 * @autor Nacho Mezzadra
 */
@Configuration
public class RedisConfig {

  @Bean
  ChannelTopic topic() {
    return new ChannelTopic("some_topic");
  }

  @Bean
  public RedisTemplate<String, String> redisTemplate(RedissonConnectionFactory connectionFactory) {
    final RedisTemplate<String, String> template = new RedisTemplate<>();
    template.setConnectionFactory(connectionFactory);
    template.setValueSerializer(new Jackson2JsonRedisSerializer<>(String.class));
    return template;
  }

  @Bean
  MessageListenerAdapter messageListener(RedisMessageSubscriber redisMessageSubscriber) {
    return new MessageListenerAdapter(redisMessageSubscriber);
  }

  @Bean
  RedissonConnectionFactory connectionFactory() {
    return new RedissonConnectionFactory();
  }

  @Bean
  RedisMessageListenerContainer redisContainer(RedisConnectionFactory connectionFactory,
      MessageListenerAdapter messageListener) {
    RedisMessageListenerContainer container
        = new RedisMessageListenerContainer();
    container.setConnectionFactory(connectionFactory);
    container.addMessageListener(messageListener, topic());
    return container;
  }

}

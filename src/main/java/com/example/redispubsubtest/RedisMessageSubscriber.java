package com.example.redispubsubtest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Service;

/**
 * @autor Nacho Mezzadra
 */
@Service
public class RedisMessageSubscriber implements MessageListener {

  Logger logger = LoggerFactory.getLogger(getClass());


  @Override
  public void onMessage(final Message message, final byte[] bytes) {
    logger.info("Message received body: {}", new String(message.getBody()));
    logger.info("Message received bytes: {}", new String(bytes));
  }


}

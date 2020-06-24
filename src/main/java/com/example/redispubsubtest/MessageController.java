package com.example.redispubsubtest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @autor Nacho Mezzadra
 */
@RestController(value = "/rest")
public class MessageController {

  private final RedisMessagePublisher publisher;

  public MessageController(RedisMessagePublisher publisher) {
    this.publisher = publisher;
  }

  @PostMapping
  public void publish(@RequestBody String message) {
    publisher.publish(message);
  }

}

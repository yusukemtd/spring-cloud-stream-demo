package com.example.streamsink;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

@SpringBootApplication
@EnableBinding(Sink.class)
public class StreamSinkApplication {

  public static void main(String[] args) {
    SpringApplication.run(StreamSinkApplication.class, args);
  }

  @StreamListener(Sink.INPUT)
  public void print(Tweet tweet) {
    System.out.println("Received " + tweet.text);
  }

  public static class Tweet {
    public String text;
  }
}

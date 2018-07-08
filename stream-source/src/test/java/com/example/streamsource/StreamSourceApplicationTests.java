package com.example.streamsource;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.cloud.stream.test.binder.MessageCollector;
import org.springframework.messaging.Message;
import org.springframework.test.context.junit4.SpringRunner;
import com.example.streamsource.StreamSourceApplication.Tweet;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class StreamSourceApplicationTests {

  @Autowired
  StreamSourceApplication app;
  @Autowired
  MessageCollector collector;
  @Autowired
  Source source;

  @Test
  @SuppressWarnings("unchecked")
  public void testTweet() {
    Tweet tweet = new Tweet();
    tweet.text = "test";
    app.tweet(tweet);

    Message<String> message = (Message<String>) collector.forChannel(source.output()).poll();

    assertThat(message.getPayload()).isInstanceOf(String.class);
    assertThat(message.getPayload()).isEqualTo("{\"text\":\"test\"}");
    assertThat(message.getHeaders().get("contentType").toString())
        .isEqualTo("application/json;charset=UTF-8");
  }

}

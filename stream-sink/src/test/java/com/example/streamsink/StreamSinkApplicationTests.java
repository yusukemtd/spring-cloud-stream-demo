package com.example.streamsink;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.rule.OutputCapture;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.test.context.junit4.SpringRunner;
import com.example.streamsink.StreamSinkApplication.Tweet;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class StreamSinkApplicationTests {

  @Autowired
  Sink sink;
  @Rule
  public OutputCapture capture = new OutputCapture();

  @Test
  public void tweetTest() {
    Tweet tweet = new Tweet();
    tweet.text = "test";
    sink.input().send(MessageBuilder.withPayload(tweet).build());
    
    assertThat(capture.toString()).isEqualTo("Received test" + System.lineSeparator());
  }

}

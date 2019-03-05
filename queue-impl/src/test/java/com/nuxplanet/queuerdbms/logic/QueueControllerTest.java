package com.nuxplanet.queuerdbms.logic;

import com.nuxplanet.queuerdbms.logic.dto.QueueId;
import com.nuxplanet.queuerdbms.logic.dto.QueueItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ComponentScan("com.nuxplanet")
public class QueueControllerTest {


    @Autowired
    private TestRestTemplate restTemplate;

    @BeforeEach
    public void setup() {
        while (restTemplate.getForObject("/queue/peek", QueueItem.class) != null);
    }


    @Test
    public void shouldPutToQueue() throws Exception{
        // when
        QueueId id = restTemplate.getForObject("/queue/put/test", QueueId.class);

        // then
        assertThat(id).isNotNull();
    }

    @Test
    public void shouldPeek() {
        // given
        QueueId id = restTemplate.getForObject("/queue/put/test", QueueId.class);


        QueueItem item = restTemplate.getForObject("/queue/peek", QueueItem.class);

        assertThat(item.getId()).isEqualTo(id);
    }

}

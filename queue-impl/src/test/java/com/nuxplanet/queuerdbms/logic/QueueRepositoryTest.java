package com.nuxplanet.queuerdbms.logic;

import com.nuxplanet.queuerdbms.logic.dto.QueueItemCommand;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ComponentScan("com.nuxplanet")
public class QueueRepositoryTest {

    @Autowired
    private QueueRepository repository;

    @Test
    public void should_add_new() {
        System.out.println("id: " + repository.saveNew(new QueueItemCommand("new", "some data")));
    }

}

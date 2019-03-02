package com.nuxplanet.queuerdbms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.nuxplanet")
public class QueueRDBMSApplication {
    public static void main(String[] args) {
        SpringApplication.run(QueueRDBMSApplication.class, args);
    }
}

package com.nuxplanet.queuerdbms.logic.dto;

import lombok.Value;

@Value
public class QueueEmailCommand {
    private final String status;
    private final String someData;
}

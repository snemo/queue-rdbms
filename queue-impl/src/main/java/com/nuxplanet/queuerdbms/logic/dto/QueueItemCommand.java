package com.nuxplanet.queuerdbms.logic.dto;

import lombok.Value;

@Value
public class QueueItemCommand {
    private final String status;
    private final String someData;
}

package com.nuxplanet.queuerdbms.logic.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Value;

@Value
public class QueueId {
    @JsonValue
    private final long id;

    @JsonCreator
    public static QueueId of(Long id) {
        return new QueueId(id);
    }
}

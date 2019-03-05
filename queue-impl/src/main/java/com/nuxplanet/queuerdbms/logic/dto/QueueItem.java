package com.nuxplanet.queuerdbms.logic.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
public class QueueItem {

    private QueueId id;
    private final String status;
    private final String someData;

    @JsonCreator
    public QueueItem(
            @JsonProperty("id") QueueId id,
            @JsonProperty("status") String status,
            @JsonProperty("someData") String someData) {
        this.id = id;
        this.status = status;
        this.someData = someData;
    }

}

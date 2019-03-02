package com.nuxplanet.queuerdbms.logic;

import java.time.LocalDateTime;

class QueueItem {

    private LocalDateTime arrivedTime;
    private Status status;
    private DataElement dataElement;

    enum Status {
        NEW, PROCESSING, DONE
    }
}

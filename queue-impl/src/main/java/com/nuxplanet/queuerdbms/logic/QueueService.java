package com.nuxplanet.queuerdbms.logic;

import com.nuxplanet.queuerdbms.logic.dto.QueueId;
import com.nuxplanet.queuerdbms.logic.dto.QueueItem;
import com.nuxplanet.queuerdbms.logic.dto.QueueItemCommand;

import java.util.Optional;

public interface QueueService {

   QueueId add(QueueItemCommand command);

   Optional<QueueItem> peek();

   boolean remove(QueueId id);
}

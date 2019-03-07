package com.nuxplanet.queuerdbms.logic;

import com.nuxplanet.queuerdbms.logic.dto.QueueItemCommand;
import com.nuxplanet.queuerdbms.logic.dto.QueueId;
import com.nuxplanet.queuerdbms.logic.dto.QueueItem;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
class QueueRDBMSService implements QueueService {

    private static final Logger log = LoggerFactory.getLogger(QueueRDBMSService.class);

    private final QueueRepository queueRepository;

    @Override
    @Transactional
    public QueueId add(QueueItemCommand command) {
        QueueId queueId = queueRepository.saveNew(command);
        log.info("Added new item to Queue: id " + queueId);
        return queueId;
    }

    @Override
    public boolean remove(QueueId id) {
        return queueRepository.compareAndSetStatus(id, "processing", "done");
    }

    @Transactional
    public Optional<QueueItem> peek() {
        QueueItem item;

        while ((item = queueRepository.findOldestItemToProcess()) != null) {
            if (queueRepository.compareAndSetStatus(item.getId(), "new", "processing")) {
                break;
            }
        }

        if (item != null) {
            log.info("Peeked element: " + item.getId());
        }
        return Optional.ofNullable(item);
    }
}

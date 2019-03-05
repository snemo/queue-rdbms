package com.nuxplanet.queuerdbms.logic;

import com.nuxplanet.queuerdbms.logic.dto.QueueEmailCommand;
import com.nuxplanet.queuerdbms.logic.dto.QueueId;
import com.nuxplanet.queuerdbms.logic.dto.QueueItem;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
class QueueService {

    private final QueueRepository queueRepository;

    @Transactional
    public QueueId put(QueueEmailCommand command) {
        return queueRepository.saveNew(command);
    }

    @Transactional
    public Optional<QueueItem> peek() {
        QueueItem item;
        for (;;) {
            item = queueRepository.findOldestItemToProcess();

            if (item == null) {
                break;
            }

            if (queueRepository.compareAndSetStatus(item.getId(), "new", "processing")) {
                break;
            }
        }
        return Optional.ofNullable(item);
    }
}

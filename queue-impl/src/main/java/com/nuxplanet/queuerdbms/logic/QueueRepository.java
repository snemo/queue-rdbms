package com.nuxplanet.queuerdbms.logic;

import com.nuxplanet.generated.tables.EmailQueue;
import com.nuxplanet.queuerdbms.logic.dto.QueueEmailCommand;
import com.nuxplanet.queuerdbms.logic.dto.QueueId;
import com.nuxplanet.queuerdbms.logic.dto.QueueItem;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import static com.nuxplanet.generated.tables.EmailQueue.EMAIL_QUEUE;

@Repository
class QueueRepository {

    private final DSLContext dsl;

    QueueRepository(DSLContext dsl) {
        this.dsl = dsl;
    }

    QueueId saveNew(QueueEmailCommand emailCommand) {
        Record record = dsl.insertInto(EMAIL_QUEUE)
                .set(EMAIL_QUEUE.VERSION, 0L)
                .set(EMAIL_QUEUE.STATUS, emailCommand.getStatus())
                .set(EMAIL_QUEUE.SOME_DATA, emailCommand.getSomeData())
                .set(EMAIL_QUEUE.CREATED_DATE, Timestamp.valueOf(LocalDateTime.now()))
                .returning(EMAIL_QUEUE.ID)
                .fetchOne();

        return new QueueId(record.getValue(EMAIL_QUEUE.ID));
    }

    QueueItem findOldestItemToProcess() {
        Record record = dsl.selectFrom(EMAIL_QUEUE)
                .where(EMAIL_QUEUE.STATUS.eq("new"))
                .orderBy(EMAIL_QUEUE.CREATED_DATE)
                .limit(1)
                .fetchOne();

        if (record == null) {
            return null;
        }

        return new QueueItem(
                QueueId.of(record.getValue(EMAIL_QUEUE.ID)),
                record.getValue(EMAIL_QUEUE.STATUS),
                record.getValue(EMAIL_QUEUE.SOME_DATA));
    }

    boolean compareAndSetStatus(QueueId id, String expect, String update) {
        int result = dsl.update(EMAIL_QUEUE)
                .set(EMAIL_QUEUE.STATUS, update)
                .where(EMAIL_QUEUE.ID.eq(id.getId())
                                .and(EMAIL_QUEUE.STATUS.eq(expect)))
                .execute();

        return result == 1;
    }

}

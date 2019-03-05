package com.nuxplanet.queuerdbms.logic;

import com.nuxplanet.queuerdbms.logic.dto.QueueEmailCommand;
import com.nuxplanet.queuerdbms.logic.dto.QueueId;
import com.nuxplanet.queuerdbms.logic.dto.QueueItem;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/queue")
@AllArgsConstructor
class QueueController {

    private final QueueService queueService;

    @GetMapping("/put/{someData}")
    public QueueId put(@PathVariable String someData) {
        return queueService.put(new QueueEmailCommand("new", someData));
    }

    @GetMapping("/peek")
    public Optional<QueueItem> peek() {
        return queueService.peek();
    }


}

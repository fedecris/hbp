package health.hbp.service;

import health.hbp.model.Event;
import health.hbp.msg.KafkaConsumer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service @RequiredArgsConstructor
public class EventsServiceImpl implements EventsService {

    private final KafkaConsumer consumer;

    @Override
    public List<Event> retrieveAllEvents() {
        return consumer.getEvents();
    }
}

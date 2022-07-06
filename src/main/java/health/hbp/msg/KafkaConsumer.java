package health.hbp.msg;

import com.fasterxml.jackson.databind.ObjectMapper;
import health.hbp.model.Event;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component @Slf4j
public class KafkaConsumer {

    public List<Event> events = new ArrayList<>();

    @KafkaListener(topics = "eventos", groupId = "group_id")
    public void consume(String payload) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Event event = objectMapper.readValue(payload, Event.class);
            events.add(event);
        } catch (Exception e) {
            log.warn("Event error: " + e);
        }
    }

    public List<Event> getEvents() {
        return events;
    }
}

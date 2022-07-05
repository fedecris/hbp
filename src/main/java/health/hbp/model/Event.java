package health.hbp.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class Event {

    public Event(LocalDateTime time, String action, String id) {
        this.time = time;
        this.action = action;
        this.id = id;
    }

    private LocalDateTime time;

    private String action;

    private String id;
}

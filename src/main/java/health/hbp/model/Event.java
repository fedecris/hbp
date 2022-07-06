package health.hbp.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter @Setter
public class Event {

    public Event() {
    }

    public Event(Date time, String action, String id) {
        this.time = time;
        this.action = action;
        this.id = id;
    }

    private Date time;

    private String action;

    private String id;
}

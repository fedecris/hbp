package health.hbp.service;

import health.hbp.model.Event;

import java.util.List;

public interface EventsService {

    List<Event> retrieveAllEvents();
}

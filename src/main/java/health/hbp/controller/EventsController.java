package health.hbp.controller;

import health.hbp.model.Event;
import health.hbp.service.EventsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller @RequiredArgsConstructor
public class EventsController {

    private final EventsService service;

    @GetMapping("/eventos")
    public ResponseEntity<List<Event>> getEvents() {
        return new ResponseEntity<>(service.retrieveAllEvents(), HttpStatus.OK);
    }
}

package health.hbp.controller;

import health.hbp.service.MetricService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller @RequiredArgsConstructor
public class MetricController {

    private final MetricService service;

    @GetMapping(value = "/metric")
    @ResponseBody
    public Map getMetric() {
        return service.getFullMetric();
    }
}

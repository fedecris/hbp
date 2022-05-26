package health.hbp.monitor;

import health.hbp.repository.EdibleRepository;
import health.hbp.repository.UserRepository;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Endpoint(id = "stats")
@Component
public class StatsActuatorEndpoint {

    private final EdibleRepository edibleRepository;
    private final UserRepository userRepository;

    public StatsActuatorEndpoint(EdibleRepository edibleRepository, UserRepository userRepository) {
        this.edibleRepository = edibleRepository;
        this.userRepository = userRepository;
    }

    @ReadOperation
    public Object statsEndpoint() {
        Map<String, Long> data = new HashMap<>();
        data.put("Edible count", edibleRepository.count());
        data.put("User count", userRepository.count());
        return data;
    }
}

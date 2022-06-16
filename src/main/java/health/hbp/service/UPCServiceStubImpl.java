package health.hbp.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.concurrent.TimeUnit;

@Service
@Profile({"dev", "test"})
public class UPCServiceStubImpl implements UPCService {

    @Override
    public String upcLookup(String upc) throws ResponseStatusException {
        try {
            // Simulate some delay
            TimeUnit.SECONDS.sleep(2L);
        } catch (Exception e) {}
        return String.format("{ %s : %s } ", "\"name\"", "\"FOOBAR Cookie\"");
    }
}

package health.hbp.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@Profile({"dev", "test"})
public class UPCServiceStubImpl implements UPCService {

    @Override
    public String upcLookup(String upc) throws ResponseStatusException {
        return String.format("{ %s : %s } ", "\"name\"", "\"galletitas sin sal\"");
    }
}

package health.hbp.service;

import org.springframework.web.server.ResponseStatusException;

public interface UPCService {

    String upcLookup (String upc) throws ResponseStatusException;

}

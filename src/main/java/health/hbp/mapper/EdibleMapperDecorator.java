package health.hbp.mapper;

import health.hbp.dto.EdibleDTO;
import health.hbp.model.Edible;
import health.hbp.service.PreferencesService;
import health.hbp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public abstract class EdibleMapperDecorator implements EdibleMapper {

    @Autowired
    @Qualifier("delegate")
    private EdibleMapper delegate;

    @Autowired
    private PreferencesService preferencesService;

    @Autowired
    private UserService userService;

    @Override
    public EdibleDTO edibleToEdibleDTO(Edible edible) {
        EdibleDTO edibleDTO = delegate.edibleToEdibleDTO(edible);
        edibleDTO.setPortionsToSodiumLimit(calculatePortionsToSodiumLimit(edible));
        return edibleDTO;
    }

    /** Number of portions until reach sodium limit */
    protected String calculatePortionsToSodiumLimit(Edible edible) {
        // User must be logged-in
        if (!userService.getLoggedUser().isPresent())
            return "-";
        // Calculate portions based on preferences
        Double portionsToSodiumLimit = null;
        Double dailySodiumLimit = 2.0;
        try {
            dailySodiumLimit = preferencesService.getPreferencesForLoggedUser().getDailySodiumLimit();
            portionsToSodiumLimit = Math.floor(dailySodiumLimit * edible.getFacts().getPortion() / edible.getFacts().getSodium() / edible.getFacts().getPortion() * 10) / 10;
        } catch (Exception e) { /* Some fact or preference not configured */ }
        return portionsToSodiumLimit == null ? "Check config." : (portionsToSodiumLimit.isInfinite() ? "∞" : "< " + portionsToSodiumLimit.intValue());
    }
}

package health.hbp.mapper;

import health.hbp.dto.EdibleDTO;
import health.hbp.model.Edible;
import health.hbp.repository.PreferencesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public abstract class EdibleMapperDecorator implements EdibleMapper {

    @Autowired
    @Qualifier("delegate")
    private EdibleMapper delegate;

    @Autowired
    private PreferencesRepository preferencesRepository;

    @Override
    public EdibleDTO edibleToEdibleDTO(Edible edible) {
        EdibleDTO edibleDTO = delegate.edibleToEdibleDTO(edible);
        edibleDTO.setPortionsToSodiumLimit(calculatePortionsToSodiumLimit(edible));
        return edibleDTO;
    }

    /** Number of portions until reach sodium limit */
    protected String calculatePortionsToSodiumLimit(Edible edible) {
        Double portionsToSodiumLimit = null;
        try {
            Double dailySodiumLimit = preferencesRepository.findAll().get(0).getDailySodiumLimit();
            portionsToSodiumLimit = Math.floor(dailySodiumLimit * edible.getFacts().getPortion() / edible.getFacts().getSodium() / edible.getFacts().getPortion() * 10) / 10;
        } catch (Exception e) { /* Some fact or preference not configured */ }
        return portionsToSodiumLimit == null ? "Check conf." : (portionsToSodiumLimit.isInfinite() ? "All that you want!" : "Less than " + portionsToSodiumLimit.intValue());
    }
}

package health.hbp.service;

import health.hbp.model.Preferences;
import health.hbp.model.User;
import health.hbp.repository.PreferencesRepository;
import health.hbp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class PreferencesService {

    PreferencesRepository repository;

    UserRepository userRepository;

    @Autowired
    public PreferencesService(PreferencesRepository repository, UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    public Preferences getPreferencesForLoggedUser(boolean createNew) {
        if (repository.findByUser(getLoggedUser()) == null && createNew) {
            Preferences preferences = new Preferences();
            preferences.setUser(getLoggedUser());
            preferences.setDailySodiumLimit(1.5);
            repository.save(preferences);
        }
        return repository.findByUser(getLoggedUser());
    }

    public void savePreferencesForLoggedUser(Preferences preferences) {
        preferences.setUser(getLoggedUser());
        repository.save(preferences);
    }

    protected User getLoggedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findByUsername(authentication.getName());
    }
}

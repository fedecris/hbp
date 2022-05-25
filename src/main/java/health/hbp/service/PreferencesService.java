package health.hbp.service;

import health.hbp.model.Preferences;
import health.hbp.model.User;
import health.hbp.repository.PreferencesRepository;
import health.hbp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PreferencesService {

    PreferencesRepository repository;

    UserService userService;

    @Autowired
    public PreferencesService(PreferencesRepository repository, UserService userService) {
        this.repository = repository;
        this.userService = userService;
    }

    public Preferences getPreferencesForLoggedUser(boolean createNew) {
        Optional<User> user = userService.getLoggedUser();
        if (!user.isPresent() && createNew) {
            Preferences preferences = new Preferences();
            preferences.setDailySodiumLimit(1.5);
            repository.save(preferences);
        }
        return repository.findByUser(user.get());
    }

    public void savePreferencesForLoggedUser(Preferences preferences) {
        preferences.setUser(userService.getLoggedUser().get());
        repository.save(preferences);
    }

}

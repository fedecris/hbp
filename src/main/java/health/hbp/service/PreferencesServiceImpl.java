package health.hbp.service;

import health.hbp.model.Preferences;
import health.hbp.model.User;
import health.hbp.repository.PreferencesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service @RequiredArgsConstructor
public class PreferencesServiceImpl implements PreferencesService {

    private final PreferencesRepository repository;

    private final UserService userService;

    public Preferences getPreferencesForLoggedUser() {
        Optional<User> user = userService.getLoggedUser();
        if (!user.isPresent()) {
            return null;
        }
        Preferences pref = repository.findByUser(user.get());
        if (pref==null) {
            pref = new Preferences();
            pref.setDailySodiumLimit(1.5);
            pref.setUser(user.get());
            repository.save(pref);
        }
        return pref;
    }

    public void savePreferencesForLoggedUser(Preferences preferences) {
        preferences.setUser(userService.getLoggedUser().get());
        repository.save(preferences);
    }

}

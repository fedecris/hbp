package health.hbp.service;

import health.hbp.model.Preferences;

public interface PreferencesService {

    public Preferences getPreferencesForLoggedUser();

    public void savePreferencesForLoggedUser(Preferences preferences);
}

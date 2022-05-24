package health.hbp.repository;

import health.hbp.model.Preferences;
import health.hbp.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PreferencesRepository extends MongoRepository<Preferences, String> {

    Preferences findByUser(User user);

}

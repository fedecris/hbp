package health.hbp.repository;

import health.hbp.model.Preferences;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PreferencesRepository extends MongoRepository<Preferences, String> {

}

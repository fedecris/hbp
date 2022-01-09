package health.hbp.repository;

import com.mongodb.lang.Nullable;
import health.hbp.model.Edible;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EdibleRepository extends MongoRepository<Edible, String> {

    default Edible upsert(Edible edible) {
        if (edible.getId()==null || edible.getId().length()==0) {
            edible.setId(null);
            insert(edible);
        } else {
            save(edible);
        }
        return edible;
    }
}


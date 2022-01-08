package health.hbp.repository;

import health.hbp.model.Edible;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EdibleRepository extends MongoRepository<Edible, String> {

    default Edible upsert(Edible edible) {
        if (edible.getId()==null || edible.getId().length()==0) {
            edible.setId(null);
            insert(edible);
            return edible;
        } else {
            if (findById(edible.getId()).isPresent()) {
                Edible updatedEdible = findById(edible.getId()).get();
                updatedEdible.setName(edible.getName());
                updatedEdible.setPortion((edible.getPortion()));
                save(updatedEdible);
                return updatedEdible;
            } else {
                save(edible);
                return edible;
            }
        }
    }
}

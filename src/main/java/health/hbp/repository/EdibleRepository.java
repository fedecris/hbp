package health.hbp.repository;

import com.mongodb.lang.Nullable;
import health.hbp.model.Edible;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface EdibleRepository extends MongoRepository<Edible, String> {

    List<Edible> findByNameLike(String criteria, Sort sort);

    List<Edible> findByBrandLike(String criteria, Sort sort);

    List<Edible> findByUpc(String criteria);

}


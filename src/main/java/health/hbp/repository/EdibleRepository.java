package health.hbp.repository;

import com.mongodb.lang.Nullable;
import health.hbp.model.Edible;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface EdibleRepository extends MongoRepository<Edible, String> {

    List<Edible> findByNameLikeIgnoreCase(String criteria, Sort sort);

    List<Edible> findByBrandLikeIgnoreCase(String criteria, Sort sort);

    List<Edible> findByUpcLike(String criteria);

}


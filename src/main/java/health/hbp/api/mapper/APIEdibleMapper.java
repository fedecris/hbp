package health.hbp.api.mapper;

import health.hbp.api.stub.model.Edible;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface APIEdibleMapper {

    Edible map(health.hbp.model.Edible edible);
}

package health.hbp.mapper;

import health.hbp.dto.EdibleDTO;
import health.hbp.model.Edible;
import org.mapstruct.*;
import org.springframework.context.annotation.Primary;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
@Primary
@DecoratedWith(EdibleMapperDecorator.class)
public interface EdibleMapper {

    // variables with same name (line name or upc) are automatically mapped
    EdibleDTO edibleToEdibleDTO(Edible edible);

    @AfterMapping
    default void calculateSaturation(Edible edible, @MappingTarget EdibleDTO edibleDTO) {
        Double saturation = null;
        try {
            Double portion = edible.getFacts().getPortion();
            Double sodium = edible.getFacts().getSodium();
            saturation = Math.floor(sodium / portion * 10) / 100;
        } catch (Exception e) { /* Some value was not loaded */}
        edibleDTO.setSaturationSodium(saturation);
    }


}

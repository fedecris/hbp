package health.hbp.mapper;

import health.hbp.dto.EdibleDTO;
import health.hbp.model.Edible;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface EdibleMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    EdibleDTO edibleToEdibleDTO(Edible edible);

    @AfterMapping
    default void calculateSaturation(Edible edible, @MappingTarget EdibleDTO edibleDTO) {
        Double saturation = null;
        try {
            Double portion = edible.getPortion();
            Double sodium = edible.getFacts().getSodium();
            saturation = sodium / portion / 10;  // sodium (mg) / 1000 / portion (g) * 100 (percentage)
            saturation = Math.floor(saturation * 100) / 100; // 2 decimal places
        } catch (Exception e) { /* Some value was not loaded */}
        edibleDTO.setSaturationSodium(saturation);
    }


}

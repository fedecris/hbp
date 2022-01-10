package health.hbp.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EdibleDTO {

    /** entity id */
    private String id;
    /** entity name */
    private String name;
    /** Saturation (%) */
    private Double saturationSodium;
}

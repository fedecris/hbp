package health.hbp.dto;

import health.hbp.model.Facts;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EdibleDTO {

    /** entity id */
    private String id;
    /** entity name */
    private String name;
    /** entity brand */
    private String brand;
    /** entity upc or ean*/
    private String upc;
    /** Nutrition facts */
    private Facts facts;
    /** Saturation (%) */
    private Double saturationSodium;
    /** portions to reach sodium limit */
    private String portionsToSodiumLimit;
}

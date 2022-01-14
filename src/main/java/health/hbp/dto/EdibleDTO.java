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
    /** entity brand */
    private String brand;
    /** entity upc or ean*/
    private String upc;
    /** Saturation (%) */
    private Double saturationSodium;
    /** portions to reach sodium limit */
    private Double portionsToSodiumLimit;
}

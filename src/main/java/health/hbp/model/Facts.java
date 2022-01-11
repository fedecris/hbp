package health.hbp.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Nutrition Fact
 */

@Getter
@Setter
public class Facts {

    Double portion;
    Double sodium;
    Double potassium;

    public Facts() {
    }

}

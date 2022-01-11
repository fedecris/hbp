package health.hbp.model;

/**
 * fit or suitable to be eaten.
 */

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Document(collection = "edibles")
@Getter
@Setter
public class Edible {

    @Id
    private String id;
    @NotNull
    private String name;
    @Null
    private String brand;
    @Null
    private String upc;
    @Null
    private Facts facts;

    public Edible() {
    }

    public Edible(String name) {
        this.name = name;
    }

    public Edible(String name, Facts facts) {
        this.name = name;
        this.facts = facts;
    }
}

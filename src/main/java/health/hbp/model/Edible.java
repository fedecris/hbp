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
    @NotNull
    private Double portion;
    @Null
    private Facts facts;

    public Edible() {
    }

    public Edible(String name, Double portion) {
        this.name = name;
        this.portion = portion;
    }

    public Edible(String name, Double portion, Facts fact) {
        this.name = name;
        this.portion = portion;
        this.facts = facts;
    }
}

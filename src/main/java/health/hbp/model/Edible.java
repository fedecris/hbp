package health.hbp.model;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

import javax.validation.constraints.NotNull;

import java.util.Map;

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
//    @Field
//    private Map<String, Double> attributes;


    public Edible() {
    }

    public Edible(String name, Double portion) {
        this.name = name;
        this.portion = portion;
    }

}

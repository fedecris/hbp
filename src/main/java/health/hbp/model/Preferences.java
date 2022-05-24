package health.hbp.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Document(collection = "preferences")
@Getter
@Setter
public class Preferences {

    @Id
    private String id;

    @NotNull
    User user;

    @NotNull
    private Double dailySodiumLimit;


}

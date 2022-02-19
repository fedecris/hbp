package health.hbp.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Document(collection = "users")
@Getter
@Setter
public class User {

    @Id
    private String id;
    @NotNull
    private String username;
    @NotNull
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}

package health.hbp.security;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import health.hbp.model.User;
import health.hbp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@Primary
public class MongoDBAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    UserRepository repository;

    @Override
    public Authentication authenticate(Authentication auth) throws AuthenticationException {
        String name = auth.getName();
        String pass = auth.getCredentials().toString();

        User user = repository.findByUsername(name);
        Argon2 a2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        if (user == null || !a2.verify(user.getPassword(), pass)) {
            return null;
        }
        return new UsernamePasswordAuthenticationToken(name, pass, new ArrayList<>());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

    // Hash pass creation for test-purposes only
    public static void main(String[] args) {
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        System.out.println(argon2.hash(1, 1024, 1, "1234"));
    }
}

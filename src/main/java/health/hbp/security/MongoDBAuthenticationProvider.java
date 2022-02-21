package health.hbp.security;

import health.hbp.model.User;
import health.hbp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class MongoDBAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    UserRepository repository;

    @Override
    public Authentication authenticate(Authentication auth) throws AuthenticationException {
        String name = auth.getName();
        String pass = auth.getCredentials().toString();

        User user = repository.findByUsername(name);
        if (user == null || !user.getPassword().equals(pass) ) {
            return null;
        }
        return new UsernamePasswordAuthenticationToken(name, pass, new ArrayList<>());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}

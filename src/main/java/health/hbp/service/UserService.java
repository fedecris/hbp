package health.hbp.service;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import health.hbp.model.User;
import health.hbp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserService {

    protected final UserRepository repository;

    @Autowired
    protected MessageSource messageSource;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    /** Registers a user.
     * @return an optional containing an error message related with the registration process or an empty optional otherwise */
    public Optional<String> register(String username, String password, String passwordCheck) {
        // User already exists?
        if (repository.findByUsername(username) != null) {
            return Optional.of(messageSource.getMessage("user.exists", null, LocaleContextHolder.getLocale()));
        }

        // Pass & check matches?
        if (!password.equals(passwordCheck)) {
            return Optional.of(messageSource.getMessage("password.mismatch", null, LocaleContextHolder.getLocale()));
        }

        // Create new user
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hashedPass = argon2.hash(1, 1024, 1, password);
        User user = new User(username, hashedPass);
        repository.save(user);
        return Optional.empty();
    }

    /** @return the logged-in ser */
    public Optional<User> getLoggedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = repository.findByUsername(authentication.getName());
        if (user == null)
            return Optional.empty();
        return Optional.of(user);
    }
}

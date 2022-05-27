package health.hbp.service;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import health.hbp.model.User;
import health.hbp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service @RequiredArgsConstructor @Slf4j
public class UserServiceImpl implements UserService {

    protected final UserRepository repository;

    protected final MessageSource messageSource;


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
        log.info("Creating new user {}", username);
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

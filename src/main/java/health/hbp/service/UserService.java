package health.hbp.service;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import health.hbp.model.User;
import health.hbp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
public class UserService {

    protected final UserRepository repository;

    @Autowired
    protected MessageSource messageSource;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    /** Registers a user.
     * @return null if no errors */
    public String register(String username, String password) {
        if (repository.findByUsername(username) != null) {
            return messageSource.getMessage("user.exists", null, LocaleContextHolder.getLocale());
        }
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hashedPass = argon2.hash(1, 1024, 1, password);
        User user = new User(username, hashedPass);
        repository.save(user);
        return null;
    }
}

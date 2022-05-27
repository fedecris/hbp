package health.hbp.service;

import health.hbp.model.User;

import java.util.Optional;

public interface UserService {

    public Optional<String> register(String username, String password, String passwordCheck);

    public Optional<User> getLoggedUser();
}

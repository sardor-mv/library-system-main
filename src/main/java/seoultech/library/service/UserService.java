package seoultech.library.service;

import seoultech.library.model.User;

import java.util.Optional;

public interface UserService {
    User save(User user, User actioner);
    User update(User user, User actioner);
    void updatePassword(User user, String password, User actioner);

    Optional<User> findByUsername(String username);
    Long count();
}

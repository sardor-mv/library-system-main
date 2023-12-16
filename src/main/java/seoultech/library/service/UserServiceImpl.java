package seoultech.library.service;

import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import seoultech.library.model.User;
import seoultech.library.model.UserRepository;

import java.time.OffsetDateTime;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public User save(User user, User actioner) {
        user.setCreator(actioner);
        user.setUpdater(actioner);
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public User update(User user, User actioner) {
        user.setUpdater(actioner);
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public void updatePassword(User user, String password, User actioner) {
        userRepository.updatePassword(user.getId(), passwordEncoder.encode(password), actioner, OffsetDateTime.now());
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Long count() {
        return userRepository.count();
    }
}
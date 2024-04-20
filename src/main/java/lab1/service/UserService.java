package lab1.service;

import lab1.model.user.User;
import lab1.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(IllegalArgumentException::new);
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    public User create(User user) {
        userRepository.save(user);
        return user;
    }

    public boolean checkIfUserExists(String userEmail) {
        var user = userRepository.findByEmail(userEmail);

        return user.isPresent();
    }
}

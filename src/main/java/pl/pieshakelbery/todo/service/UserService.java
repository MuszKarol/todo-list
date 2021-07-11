package pl.pieshakelbery.todo.service;

import org.springframework.stereotype.Service;
import pl.pieshakelbery.todo.entity.User;
import pl.pieshakelbery.todo.repository.UserRepository;
import pl.pieshakelbery.todo.security.MD5;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void save(User user){
        user.setPassword(MD5.generate(user.getPassword()));
        this.userRepository.save(user);
    }

    public User getUserByEmail(String email) {
        return userRepository.getUserByEmail(email);
    }
}

package pl.pieshakelbery.todo.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.pieshakelbery.todo.dto.TaskDTO;
import pl.pieshakelbery.todo.dto.UserDTO;
import pl.pieshakelbery.todo.entity.User;
import pl.pieshakelbery.todo.mapper.TaskMapper;
import pl.pieshakelbery.todo.mapper.UserMapper;
import pl.pieshakelbery.todo.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public void save(UserDTO userDTO){
        User user = UserMapper.INSTANCE.userDtoToUser(userDTO);

        String password = user.getPassword();
        password = bCryptPasswordEncoder.encode(password);
        user.setPassword(password);

        this.userRepository.save(user);
    }

    public UserDTO getUserByEmail(String email) {
        return UserMapper.INSTANCE.userToUserDTO(userRepository.getUserByEmail(email));
    }
}

package pl.pieshakelbery.todo.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import pl.pieshakelbery.todo.entity.User;
import pl.pieshakelbery.todo.repository.UserRepository;
import pl.pieshakelbery.todo.security.MyUserDetails;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.getUserByEmail(s);

        if(user == null)
            throw new UsernameNotFoundException("Could not find user by email");

        return new MyUserDetails(user);
    }
}

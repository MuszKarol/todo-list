package pl.pieshakelbery.todo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import pl.pieshakelbery.todo.entity.User;
import pl.pieshakelbery.todo.repository.UserRepository;
import pl.pieshakelbery.todo.security.MyUserDetails;

public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.getUserByEmail(s);

        if(user == null)
            throw new UsernameNotFoundException("Could not find user by email");

        return new MyUserDetails(user);
    }
}

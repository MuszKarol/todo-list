package pl.pieshakelbery.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.pieshakelbery.todo.entity.User;

import java.util.Collection;

public interface UserRepository extends JpaRepository<User, Integer> {
    public Collection<User> getAllByAgeIsGreaterThan(int age);
    public User getUserByEmail(String email);
}

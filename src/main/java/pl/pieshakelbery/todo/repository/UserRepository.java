package pl.pieshakelbery.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.pieshakelbery.todo.entity.User;

import java.util.Collection;

public interface UserRepository extends JpaRepository<User, Integer> {
    Collection<User> getAllByAgeIsGreaterThan(int age);
    User getUserByEmail(String email);
}

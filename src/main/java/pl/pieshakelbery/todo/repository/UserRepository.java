package pl.pieshakelbery.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.pieshakelbery.todo.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    User getUserByEmail(String email);
}

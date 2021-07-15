package pl.pieshakelbery.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.pieshakelbery.todo.entity.Task;
import pl.pieshakelbery.todo.entity.User;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Integer> {
    List<Task> getAllByUser(User user);
    List<Task> findAll();
    void deleteById(int id);
}

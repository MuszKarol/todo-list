package pl.pieshakelbery.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.pieshakelbery.todo.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Integer> {
}

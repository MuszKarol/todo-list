package pl.pieshakelbery.todo.service;

import org.springframework.stereotype.Service;
import pl.pieshakelbery.todo.entity.Task;
import pl.pieshakelbery.todo.entity.User;
import pl.pieshakelbery.todo.repository.TaskRepository;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getAllTasksByUser(User user) {
        return taskRepository.getAllByUser(user);
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public void deleteTaskById(int id) {
        taskRepository.deleteById(id);
    }
}

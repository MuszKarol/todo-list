package pl.pieshakelbery.todo.service;

import org.springframework.stereotype.Service;
import pl.pieshakelbery.todo.dto.TaskDTO;
import pl.pieshakelbery.todo.dto.UserDTO;
import pl.pieshakelbery.todo.entity.Task;
import pl.pieshakelbery.todo.entity.User;
import pl.pieshakelbery.todo.mapper.TaskMapper;
import pl.pieshakelbery.todo.mapper.UserMapper;
import pl.pieshakelbery.todo.repository.TaskRepository;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<TaskDTO> getAllTasksByUser(UserDTO userDTO) {
        User user = UserMapper.INSTANCE.userDtoToUser(userDTO);
        return TaskMapper.INSTANCE.taskListToTaskDtoList(taskRepository.getAllByUser(user));
    }

    public void deleteTaskById(int id) {
        taskRepository.deleteById(id);
    }

    public void save(TaskDTO taskDTO, UserDTO userDTO){
        User user = UserMapper.INSTANCE.userDtoToUser(userDTO);
        Task task = TaskMapper.INSTANCE.taskDtoToTask(taskDTO);
        task.setUser(user);
        taskRepository.save(task);
    }
}

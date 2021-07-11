package pl.pieshakelbery.todo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.pieshakelbery.todo.service.TaskService;
import pl.pieshakelbery.todo.service.UserService;

@Controller
public class TaskController {

    private final UserService userService;
    private final TaskService taskService;

    public TaskController(UserService userService, TaskService taskService) {
        this.userService = userService;
        this.taskService = taskService;
    }

    @GetMapping("/tasks")
    public String tasks(Model model) {
        model.addAttribute("tasks", taskService.getAllTasksByUser(
                userService.getUserByEmail("test@test.test")));
        return "tasks";
    }


}

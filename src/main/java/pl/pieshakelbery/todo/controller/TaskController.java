package pl.pieshakelbery.todo.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.pieshakelbery.todo.entity.Task;
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
    public String tasks( Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        model.addAttribute("tasks", taskService.getAllTasksByUser(
                userService.getUserByEmail(currentPrincipalName)));

        model.addAttribute("x", new Task());

        return "task";
    }


    @PostMapping("/save-task")
    public String createTask(@ModelAttribute Task x){
        x.setUser(userService.getUserByEmail(
                SecurityContextHolder.getContext().getAuthentication().getName()
        ));

        taskService.save(x);

        return "redirect:/tasks";
    }


    @GetMapping(value = "/delete_task/{id}")
    public String deleteTask(@PathVariable("id") int id){
        taskService.deleteTaskById(id);

        return "redirect:/tasks";
    }


}

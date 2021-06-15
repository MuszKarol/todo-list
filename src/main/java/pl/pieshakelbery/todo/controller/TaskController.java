package pl.pieshakelbery.todo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TaskController {

    @GetMapping("/random")
    public String greeting(Model model) {
        model.addAttribute("task", "random");
        return "random";
    }
}

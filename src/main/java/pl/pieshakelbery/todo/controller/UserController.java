package pl.pieshakelbery.todo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.pieshakelbery.todo.entity.User;
import pl.pieshakelbery.todo.service.UserService;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/add-user")
    public String userForm(Model model){
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping(value = "/save-user")
    public String createUser(@ModelAttribute User user) {
        System.out.println("User account has been saved");
        userService.save(user);
        return "register";
        //return "redirect:/index";
    }
}

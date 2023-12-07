package pl.pieshakelbery.todo.controller;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import pl.pieshakelbery.todo.dto.UserDTO;
import pl.pieshakelbery.todo.entity.User;
import pl.pieshakelbery.todo.service.UserService;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String userForm(Model model){
        model.addAttribute("user", new User());

        return "register";
    }

    @PostMapping(value = "/save-user")
    public String createUser(@ModelAttribute UserDTO user){
        try {
            userService.save(user);
        }catch (DataIntegrityViolationException exception){
            exception.getMessage();
            return "redirect:/register_failure_handler";
        }catch (Exception exception){
            return "redirect:/register_failure_handler";
        }

        return "redirect:/";
    }
}

package pl.pieshakelbery.todo.exceptions;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ExceptionHandlingController {

    @GetMapping("/login_failure_handler")
    public String getLoginErrorPage(Model model){
        List<String> messages = List.of("Wrong email or password.");
        model.addAttribute("messages", messages);
        return "error";
    }

    @GetMapping("/register_failure_handler")
    public String getRegisterErrorPage(Model model){
        List<String> messages = List.of("Email is already in use.");
        model.addAttribute("messages", messages);
        return "error";
    }

}

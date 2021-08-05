package pl.pieshakelbery.todo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Date;

@Getter
@AllArgsConstructor
public class UserDTO {
    public String email;
    public String password;
    public Date age;
}

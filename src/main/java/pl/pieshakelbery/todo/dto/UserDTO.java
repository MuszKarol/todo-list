package pl.pieshakelbery.todo.dto;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    public int id;
    public String email;
    public String password;
    public Date age;
}

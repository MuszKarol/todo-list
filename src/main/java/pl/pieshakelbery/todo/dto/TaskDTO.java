package pl.pieshakelbery.todo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TaskDTO {
    private String title;
    private String description;
}

package pl.pieshakelbery.todo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Repository;

import pl.pieshakelbery.todo.dto.UserDTO;
import pl.pieshakelbery.todo.entity.User;


@Repository
@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO userToUserDTO(User user);

    @Mapping(target = "email", source = "userDTO.email")
    @Mapping(target = "password", source = "userDTO.password")
    @Mapping(target = "age", source = "userDTO.age")
    User userDtoToUser(UserDTO userDTO);
}

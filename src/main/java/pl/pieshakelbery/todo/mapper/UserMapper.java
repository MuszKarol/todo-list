package pl.pieshakelbery.todo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import pl.pieshakelbery.todo.dto.UserDTO;
import pl.pieshakelbery.todo.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

//    @Mappings({
//            @Mapping(target = "id", source = "user.id"),
//            @Mapping(target = "email", source = "user.email"),
//            @Mapping(target = "password", source = "user.password"),
//            @Mapping(target = "age", source = "user.age"),
//    })
    UserDTO userToUserDTO(User user);

    @Mappings({
            @Mapping(target = "email", source = "userDTO.email"),
            @Mapping(target = "password", source = "userDTO.password"),
            @Mapping(target = "age", source = "userDTO.age"),
    })
    User userDtoToUser(UserDTO userDTO);
}

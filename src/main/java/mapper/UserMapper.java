package mapper;

import dto.UserDto;
import entity.User;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {
    User toEntity(UserDto userDto);
    UserDto toDto(User user);
}

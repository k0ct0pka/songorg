package service.entityService;

import dao.impls.UserDao;
import dto.UserDto;
import entity.User;
import factory.UserFactory;
import lombok.AllArgsConstructor;
import mapper.UserMapper;
import mapper.UserMapperImpl;

@AllArgsConstructor
public class UserService {
    private UserDao userDao;
    private UserMapper userMapper;

    public User findById(Integer id) {
        return userDao.findById(id);
    }

    public UserDto refreshUserData(UserDto user) {
        return userMapper.toDto(userDao.findById(user.getId()));
    }
}

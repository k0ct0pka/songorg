package service;

import constants.Session;
import dao.impls.UserDao;
import dto.UserDto;
import entity.User;
import factory.UserFactory;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import mapper.UserMapper;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
public class AuthorizationService {
    public static final String NAME = "name";
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";
    private final UserDao userDao;
    private final UserFactory userFactory;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserMapper userMapper;
    public void createUser(HttpServletRequest request) {
        User user = userFactory.create(getUserParams(request));
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userDao.save(user);
    }
    public void login(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Map<String, Object> userParams = getUserParams(request);
        User byEmail = userDao.findByEmail(userParams.get(EMAIL).toString());
        if (bCryptPasswordEncoder.matches(userParams.get(PASSWORD).toString(), byEmail.getPassword())) {
            UserDto userDto = userMapper.toDto(byEmail);
            session.setAttribute(Session.USER.getName(), userDto);
        } else{
            throw new BadCredentialsException("Wrong password");
        }
    }
    private Map<String,Object> getUserParams(HttpServletRequest request) {
        Map<String,Object> params = new HashMap<>();
        params.put(NAME,request.getParameter(NAME));
        params.put(EMAIL,request.getParameter(EMAIL));
        params.put(PASSWORD,request.getParameter(PASSWORD));
        return params;
    }

}

package factory;

import entity.User;
import service.AuthorizationService;

import java.util.Map;

public class UserFactory implements BaseFactory<User> {
    @Override
    public User create(Map<String, Object> params) {
        return User.builder()
                .email((String) params.get(AuthorizationService.EMAIL))
                .name((String) params.get(AuthorizationService.NAME))
                .password((String) params.get(AuthorizationService.PASSWORD))
                .build();
    }
}

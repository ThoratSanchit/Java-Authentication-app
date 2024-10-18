package javaAuthentication.app.properAuthentication.services;

import javaAuthentication.app.properAuthentication.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserService {
    User createUser(User user);
    User findByEmail(String email);
}

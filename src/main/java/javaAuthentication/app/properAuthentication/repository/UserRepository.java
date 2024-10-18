package javaAuthentication.app.properAuthentication.repository;

import javaAuthentication.app.properAuthentication.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User,String> {
    User findByEmail(String email);

}

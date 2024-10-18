package javaAuthentication.app.properAuthentication.repository;

import javaAuthentication.app.properAuthentication.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends MongoRepository<Product,String> {
}

package javaAuthentication.app.properAuthentication.services;

import javaAuthentication.app.properAuthentication.model.Product;
import javaAuthentication.app.properAuthentication.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public Product saveProductForUser(Product product) {
        return productRepository.save(product);
    }
}

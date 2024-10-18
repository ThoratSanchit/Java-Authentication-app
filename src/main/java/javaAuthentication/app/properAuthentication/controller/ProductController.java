package javaAuthentication.app.properAuthentication.controller;

import javaAuthentication.app.properAuthentication.model.Product;
import javaAuthentication.app.properAuthentication.services.ProductService;
import javaAuthentication.app.properAuthentication.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService; // Service for handling product operations

    @Autowired
    private JwtUtil jwtUtil; // JWT Utility for extracting user information

    @PostMapping("/add")
    public ResponseEntity<String> addProduct(@RequestHeader("Authorization") String token,
                                             @RequestBody Product product) {
        try {
            // Extract email from JWT token
            String userId = jwtUtil.extractUserId(token.substring(7)); // Remove "Bearer " prefix
            System.out.println("userId "+ userId);
            // Check if the email is valid (optional)
            if (userId == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
            }

            // Set user email in the product
            product.setUserId(userId); // Store the email with the product details

            // Save product details for the user
            productService.saveProductForUser(product); // Implement this method in your ProductService

            return ResponseEntity.status(HttpStatus.CREATED).body("Product added successfully");
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception for debugging
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while adding product");
        }
    }
}

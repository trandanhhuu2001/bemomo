package com.java.momo.controller;

import com.java.momo.dto.ProductResponse;
import com.java.momo.entity.Product;
import com.java.momo.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class ProductController {

    private final ProductService productService;
    @GetMapping
    public List<Product> getAllProducts(){
        return productService.getProduct();
    }
    @PostMapping
    public Product addProduct(@RequestBody Product product){
         return productService.addProduct(product);
    }

    @PostMapping("/buy")
    public ResponseEntity<String> buyProduct(@RequestBody List<ProductResponse> productResponses){
        for (ProductResponse product : productResponses) {
            System.out.println("Received product: " + product.getName() + ", Price: " + product.getPrice() + ", Quantity: " + product.getQuantity());
        }
        return new ResponseEntity<>("Sucess", HttpStatus.OK);
    }
}

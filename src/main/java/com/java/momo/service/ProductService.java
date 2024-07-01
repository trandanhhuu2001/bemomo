package com.java.momo.service;

import com.java.momo.entity.Product;
import com.java.momo.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ProductService {
    List<Product> getProduct();

    Product addProduct(Product product);
}

package com.java.momo.service.serviceimpl;

import com.java.momo.entity.Product;
import com.java.momo.repository.ProductRepository;
import com.java.momo.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    @Override
    public List<Product> getProduct() {
        return productRepository.findAll();
    }

    @Override
    public Product addProduct(Product product) {
       return productRepository.save(product);
    }
}

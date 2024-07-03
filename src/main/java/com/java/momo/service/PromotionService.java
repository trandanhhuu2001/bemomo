package com.java.momo.service;

import com.java.momo.dto.ProductResponse;
import com.java.momo.entity.Product;

import java.util.List;

public interface PromotionService {
    ProductResponse promotion(List<ProductResponse> products);
}

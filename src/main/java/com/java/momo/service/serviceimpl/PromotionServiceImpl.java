package com.java.momo.service.serviceimpl;

import com.java.momo.dto.ProductResponse;
import com.java.momo.entity.Product;
import com.java.momo.service.PromotionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PromotionServiceImpl implements PromotionService {


    @Override
    public ProductResponse promotion(List<ProductResponse> products) {
        for (ProductResponse product: products) {
            if(product.getQuantity() >= 3 ){
                double chance = Math.random();
                if(chance <= 0.1){
                    System.out.println("Chúc mừng! Bạn được nhận thêm 1 sản phẩm miễn phí.");
                }else {
                    System.out.println("Bạn không nhận được sản phẩm miễn phí lần này.");
                }
            }
        }
        return null;
    }
}

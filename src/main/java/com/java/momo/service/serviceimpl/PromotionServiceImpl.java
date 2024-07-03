package com.java.momo.service.serviceimpl;

import com.java.momo.config.DateProvider;
import com.java.momo.dto.ProductResponse;
import com.java.momo.entity.Product;
import com.java.momo.entity.Promotion;
import com.java.momo.entity.Transaction;
import com.java.momo.repository.ProductRepository;
import com.java.momo.repository.PromotionRepository;
import com.java.momo.repository.TransactionRepository;
import com.java.momo.service.ProductService;
import com.java.momo.service.PromotionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PromotionServiceImpl implements PromotionService {
    private final DateProvider dateProvider;

    private final ProductRepository productRepository;
    private final PromotionRepository promotionRepository;
    private final TransactionRepository transactionRepository;

    @Override
    public ProductResponse promotion(List<ProductResponse> products) {
        LocalDate today = dateProvider.getToday();
        LocalDate yesterday = dateProvider.getToday().minusDays(1);
        Promotion promotionToday = new Promotion();
        Optional<Promotion> promotionOptional = promotionRepository.findByStartDate(today);
        promotionToday = promotionOptional.get();
        List<Transaction> transactionList = transactionRepository.findAll().stream()
                .filter(transaction -> isTransactionOnDate(transaction, today))
                .collect(Collectors.toList());
        for (ProductResponse product : products) {
            if (product.getQuantity() >= 3) {
                Boolean check = true;
                for (int i = 0; i < transactionList.size(); i++){
                    // Kiểm tra 10 giao dịch gần nhất đã có khuyến mãi chưa
                    if(transactionList.get(i).isReceivedPromotion())
                        check = false;
                }
                // Nếu chưa có thì ramdom khuyến mãi
                if(check == true){
                    Random random = new Random();
                        if(random.nextDouble() <= 0.1){
                           // Ngẫu nhiên lần đó có được khuyến mãi không
                        }
                }
                //Nếu chưa có lần nào thì lần thứ 10 chắc chắn được khuyến mãi
                if(check == false){

                }
            }
        }
        return null;
    }

    private boolean isTransactionOnDate(Transaction transaction, LocalDate date) {
        LocalDate transactionDate = transaction.getTimestamp().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return transactionDate.equals(date);
    }
}


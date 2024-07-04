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
        List<Product> productList = productRepository.findAll();
        Optional<Promotion> promotionOptional = promotionRepository.findByStartDate(today);
        promotionToday = promotionOptional.get();
        Double bugdet = promotionToday.getBudget();
        List<Transaction> transactionList = transactionRepository.findAll().stream()
                .filter(transaction -> isTransactionOnDate(transaction, today))
                .collect(Collectors.toList());
        if(bugdet > 0){
            for (ProductResponse product : products) {
                if (product.getQuantity() >= 3) {
                    Boolean checkPromotion = isRecentTransaction(transactionList, promotionToday.getUsageCount());
                    if(checkPromotion == false && transactionList.size() < 10){
                        // Kiểm tra nếu chưa có lần nào khuyến mãi thì random lần mua đó được khuyến mãi
                        Random random = new Random();
                        if(random.nextDouble() <= 0.1){
                            // Xác xuất lần đó nhận là 10%
                            findRandomPromotion(productList,bugdet);
                        }
                    }
                    if(checkPromotion == false && transactionList.size() == 10){
                        findRandomPromotion(productList,bugdet);
                    }
                }
            }
        }
        return null;
    }

    private boolean isTransactionOnDate(Transaction transaction, LocalDate date) {
        LocalDate transactionDate = transaction.getTimestamp().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return transactionDate.equals(date);
    }
    private boolean isRecentTransaction(List<Transaction> transactionList,int usageCount){
        Boolean check = true;
        for (int i = usageCount*10; i < transactionList.size(); i++){
            // Kiểm tra 10 giao dịch gần nhất đã có khuyến mãi chưa
            if(transactionList.get(i).isReceivedPromotion())
                check = false;
        }
        return check;
    }
    private  Product findRandomPromotion(List<Product> products, double threshold){
        if (products == null || products.isEmpty()) {
            return null;
        }
        List<Product> eligibleProducts = products.stream()
                .filter(product -> product.getPrice() < threshold)
                .toList();
        if (eligibleProducts.isEmpty()) {
            return null;
        }
        Random random = new Random();
        int randomIndex = random.nextInt(eligibleProducts.size());
        return eligibleProducts.get(randomIndex);
    }
}


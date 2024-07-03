package com.java.momo.repository;

import com.java.momo.entity.Promotion;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface PromotionRepository extends MongoRepository<Promotion, String> {
    Optional<Promotion> findByStartDate(LocalDate startDate);
}

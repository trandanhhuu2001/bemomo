package com.java.momo.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document(collection = "transactions")
public class Transaction {
    @Id
    private String id;
    private Date timestamp;
    private String productId;
    private  Double amountPaid;
    private boolean receivedPromotion;
}

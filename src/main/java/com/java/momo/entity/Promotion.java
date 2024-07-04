package com.java.momo.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@Document(collection = "promotions")
public class Promotion {
    @Id
    private String id;
    private String name;
    private String description;
    private Date startDate;
    private Date endDate;
    private List<String> productIds;
    private  int usageCount;
    private double budget;
}

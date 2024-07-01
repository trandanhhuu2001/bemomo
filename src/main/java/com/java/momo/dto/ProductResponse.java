package com.java.momo.dto;

import lombok.Data;

@Data
public class ProductResponse {
    private Integer id;
    private String name;
    private Integer quantity;
    private Integer price;
}

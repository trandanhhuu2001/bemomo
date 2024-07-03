package com.java.momo.config;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DateProvider {

    private LocalDate today;

    @PostConstruct
    public void init(){
        today = LocalDate.now();
    }
    public LocalDate getToday() {
        return today;
    }
}

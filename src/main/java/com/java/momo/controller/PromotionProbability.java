package com.java.momo.controller;

public class PromotionProbability {
    public static void main(String[] args) {
        int numberOfProductsPurchased = 3;
        double probabilityOfExtraProduct = 0.1;
        int numberOfSimulations = 100000;

        int countExtraProducts = 0;

        for (int i = 0; i < numberOfSimulations; i++) {

            double randomNumber = Math.random();

            if (randomNumber <= probabilityOfExtraProduct) {
                countExtraProducts++;
            }
        }

        double probability = (double) countExtraProducts / numberOfSimulations;

        System.out.println("Xác suất khách hàng nhận được sản phẩm khuyến mãi là: " + probability);
    }
}

package com.brandy.courses;

public record OrderRecord(
        String customerName,
        String productName,
        int quantity) { }

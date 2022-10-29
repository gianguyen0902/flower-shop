package com.FlowerShop.FlowerShop.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.Instant;
import java.time.LocalDateTime;

@Data
public class ReceiptReq {
    private int id;
    private String reciverName;
    private String address;
    private String message;
    private String phoneNum;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime deliveryDate;
}

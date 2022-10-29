package com.FlowerShop.FlowerShop.models;


import com.FlowerShop.FlowerShop.appuser.AppUser;
import lombok.Data;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
@Data
@Entity
@Table(name = "receipt")
public class Receipt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ReceiptID", nullable = false)
    private Integer id;

    @Column(name = "ReciverName", nullable = false)
    private String reciverName;

    @Column(name = "ReciverPhoneNum", nullable = false)
    private String reciverPhoneNum;

    @Column(name = "ReciverAddress", nullable = false)
    private String reciverAddress;

    @Column(name = "ExportDate", nullable = false)
    private LocalDateTime exportDate;

    @Column(name = "DeliveryDate", nullable = false)
    private Instant deliveryDate;

    @Column(name = "TotalPayMent", nullable = false)
    private Double totalPayMent;

    @Lob
    @Column(name = "Message")
    private String message;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private AppUser user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

//    public Instant getExportDate() {
//        return exportDate;
//    }
//
//    public void setExportDate(Instant exportDate) {
//        this.exportDate = exportDate;
//    }
//
//    public LocalDate getDeliveryDate() {
//        return deliveryDate;
//    }
//
//    public void setDeliveryDate(LocalDate deliveryDate) {
//        this.deliveryDate = deliveryDate;
//    }

    public Double getTotalPayMent() {
        return totalPayMent;
    }

    public void setTotalPayMent(Double totalPayMent) {
        this.totalPayMent = totalPayMent;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
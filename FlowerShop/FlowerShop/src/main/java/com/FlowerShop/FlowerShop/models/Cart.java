package com.FlowerShop.FlowerShop.models;


import com.FlowerShop.FlowerShop.appuser.AppUser;
import lombok.Data;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CartID", nullable = false)
    private Integer id;

    @Column(name = "Quantity", nullable = false)
    private int quantity;
    @Column(name = "TotalPayMent", nullable = false)
    private Double totalPayMent;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private AppUser user;

    @ManyToOne
    @JoinColumn(name = "flower_id")
    private Flower flower;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getTotalPayMent() {
        return totalPayMent;
    }

    public void setTotalPayMent(Double totalPayMent) {
        this.totalPayMent = totalPayMent;
    }


    public boolean deleted;
}
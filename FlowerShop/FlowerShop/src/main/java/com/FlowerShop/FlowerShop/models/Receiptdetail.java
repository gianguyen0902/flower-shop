package com.FlowerShop.FlowerShop.models;

import javax.persistence.*;

@Entity
@Table(name = "receiptdetail")
public class Receiptdetail {
    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    @JoinColumn(name = "ReceiptID", nullable = false)
    private Receipt receiptID;


    @ManyToOne
    @JoinColumn(name = "FlowerID", nullable = false)
    private Flower flower;

    @Column(name = "Price", nullable = false)
    private Double price;

    @Column(name = "Quantity", nullable = false)
    private Integer quantity;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Receipt getReceiptID() {
        return receiptID;
    }

    public void setReceiptID(Receipt receiptID) {
        this.receiptID = receiptID;
    }

    public Flower getFlower() {
        return flower;
    }

    public void setFlower(Flower flower) {
        this.flower = flower;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

}
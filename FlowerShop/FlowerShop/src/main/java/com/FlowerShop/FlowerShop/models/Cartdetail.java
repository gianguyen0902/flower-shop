//package com.FlowerShop.FlowerShop.models;
//
//import javax.persistence.*;
//
//@Entity
//@Table(name = "cartdetail")
//public class Cartdetail {
//    @EmbeddedId
//    private CartdetailId id;
//
//    @MapsId("cartID")
//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    @JoinColumn(name = "CartID", nullable = false)
//    private Cart cartID;
//
//    @MapsId("flowerID")
//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    @JoinColumn(name = "FlowerID", nullable = false)
//    private Flower flowerID;
//
//    @Column(name = "Quantity", nullable = false)
//    private Integer quantity;
//
//    public CartdetailId getId() {
//        return id;
//    }
//
//    public void setId(CartdetailId id) {
//        this.id = id;
//    }
//
//    public Cart getCartID() {
//        return cartID;
//    }
//
//    public void setCartID(Cart cartID) {
//        this.cartID = cartID;
//    }
//
//    public Flower getFlowerID() {
//        return flowerID;
//    }
//
//    public void setFlowerID(Flower flowerID) {
//        this.flowerID = flowerID;
//    }
//
//    public Integer getQuantity() {
//        return quantity;
//    }
//
//    public void setQuantity(Integer quantity) {
//        this.quantity = quantity;
//    }
//
//}
package com.FlowerShop.FlowerShop.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "flower")
public class Flower {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FlowerID", nullable = false)
    private Integer id;

    @Column(name = "FlowerName", nullable = false)
    private String flowerName;

    @Column(name = "Color", length = 50)
    private String color;

    @Column(name = "Image", length = 100)
    private String image;

    @Column(name = "Unit", length = 100)
    private String unit;

    @Column(name = "Price", nullable = false)
    private Double price;

    @Column(name = "Description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "CategoryID", nullable = false)
    private Category categoryID;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "SupplierID", nullable = false)
    private Supplier supplierID;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFlowerName() {
        return flowerName;
    }

    public void setFlowerName(String flowerName) {
        this.flowerName = flowerName;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getImg() {
        return image;
    }

    public void setImg(String image) {
        this.image = image;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String inventoryNumber) {
        this.description = inventoryNumber;
    }

    public Category getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(Category categoryID) {
        this.categoryID = categoryID;
    }

    public Supplier getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(Supplier supplierID) {
        this.supplierID = supplierID;
    }
    @Transient
    public String getPhotosImagePath() {
        if (image == null || id == null) return null;

        return "/flowers/" + id + "/" + image;
    }

}
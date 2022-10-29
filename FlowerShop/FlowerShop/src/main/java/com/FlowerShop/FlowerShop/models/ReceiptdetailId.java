package com.FlowerShop.FlowerShop.models;

import lombok.Data;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Data
public class ReceiptdetailId implements Serializable {
    private static final long serialVersionUID = 6132492097515502123L;
    @Column(name = "ReceiptID", nullable = false)
    private Integer receiptID;

    @Column(name = "FlowerID", nullable = false)
    private Integer flowerID;

    public Integer getReceiptID() {
        return receiptID;
    }

    public void setReceiptID(Integer receiptID) {
        this.receiptID = receiptID;
    }

    public Integer getFlowerID() {
        return flowerID;
    }

    public void setFlowerID(Integer flowerID) {
        this.flowerID = flowerID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ReceiptdetailId entity = (ReceiptdetailId) o;
        return Objects.equals(this.flowerID, entity.flowerID) &&
                Objects.equals(this.receiptID, entity.receiptID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(flowerID, receiptID);
    }

}
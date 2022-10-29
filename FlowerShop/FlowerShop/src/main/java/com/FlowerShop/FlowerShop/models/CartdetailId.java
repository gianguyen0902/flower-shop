package com.FlowerShop.FlowerShop.models;

import lombok.Data;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Data
public class CartdetailId implements Serializable {
    private static final long serialVersionUID = 2912415493505061195L;
    @Column(name = "CartID", nullable = false)
    private Integer cartID;

    @Column(name = "FlowerID", nullable = false)
    private Integer flowerID;

    public Integer getCartID() {
        return cartID;
    }

    public void setCartID(Integer cartID) {
        this.cartID = cartID;
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
        CartdetailId entity = (CartdetailId) o;
        return Objects.equals(this.cartID, entity.cartID) &&
                Objects.equals(this.flowerID, entity.flowerID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cartID, flowerID);
    }

}
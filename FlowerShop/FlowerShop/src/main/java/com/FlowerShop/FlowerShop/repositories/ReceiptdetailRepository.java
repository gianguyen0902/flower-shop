package com.FlowerShop.FlowerShop.repositories;

import com.FlowerShop.FlowerShop.models.Receiptdetail;
import com.FlowerShop.FlowerShop.models.ReceiptdetailId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReceiptdetailRepository extends JpaRepository<Receiptdetail, ReceiptdetailId> {
    @Query("select u from Receiptdetail u where u.receiptID.id= :id")
    public List<Receiptdetail> getReceiptdetailsByReceiptID(@Param("id") int id);

//    List<Receiptdetail> findAllBYCeceiptId(@Param("id") int id);
}
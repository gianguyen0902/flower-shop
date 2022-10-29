package com.FlowerShop.FlowerShop.repositories;

import com.FlowerShop.FlowerShop.models.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReceiptRepository extends JpaRepository<Receipt, Integer> {

    @Query("select a from Receipt a where a.user.id = :id")
    List<Receipt> findAllByUser(@Param ("id")int id);
}
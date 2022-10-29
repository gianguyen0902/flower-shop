package com.FlowerShop.FlowerShop.repositories;

import com.FlowerShop.FlowerShop.models.Flower;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FlowerRepository extends JpaRepository<Flower, Integer> {
    @Query(nativeQuery = true,value = "select * from flower order by flowerid desc limit 3")
    public List<Flower> getTopThreeNew();
    @Query(nativeQuery = true,value = "select * from flower order by price desc limit 3")
    public List<Flower> getTopThreeExpensive();
    @Query("select u from Flower u where u.flowerName like %:name%")
    public Page<Flower> getFlowersByName(Pageable pageable, @Param("name") String name);

    @Query("select u from Flower u where u.categoryID.id = :categoryId")
    public Page<Flower> getFlowersByCategoryID(Pageable pageable, @Param("categoryId") int categoryId);

    Flower findOneById(int id);
}
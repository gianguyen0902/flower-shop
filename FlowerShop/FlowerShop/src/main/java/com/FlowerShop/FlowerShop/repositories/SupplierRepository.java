package com.FlowerShop.FlowerShop.repositories;

import com.FlowerShop.FlowerShop.models.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier, Integer> {
}
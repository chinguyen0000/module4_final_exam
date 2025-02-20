package com.example.final_exam.repository;

import com.example.final_exam.model.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductTypeRepository extends JpaRepository<ProductType, Integer> {
    List<ProductType> findAll();

    ProductType findByName(String name);
}

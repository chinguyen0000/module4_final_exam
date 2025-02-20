package com.example.final_exam.repository;

import com.example.final_exam.model.Product;
import com.example.final_exam.model.ProductType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductRepository extends JpaRepository<Product, Integer> {
    Page<Product> findAll(Pageable pageable);

    Product save(Product product);

    Product getById(Integer id);

    @Query("SELECT p FROM Product p WHERE " +
            "(:name IS NULL OR LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%'))) AND " +
            "(:type IS NULL OR p.productType = :type) AND " +
            "(:price IS NULL or p.price = :price)")
    Page<Product> searchProducts(@Param("name") String name, @Param("type") ProductType type, @Param("price") Long price, Pageable pageable);
}

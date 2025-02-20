package com.example.final_exam.service;

import com.example.final_exam.model.Product;
import com.example.final_exam.model.ProductType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IProductService {
    Page<Product> findAll(Pageable pageable);

    void save(Product product);

    void deleteById(Integer id);

    List<ProductType> findAll();

    Product getById(Integer id);

    void deleteAllById(List<Integer> productIds);

    Page<Product> searchProducts(@Param("name") String name, @Param("type") ProductType type, @Param("price") Long price, Pageable pageable);

    ProductType findProductTypeByName(String name);
}

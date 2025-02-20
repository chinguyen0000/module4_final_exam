package com.example.final_exam.service;

import com.example.final_exam.model.Product;
import com.example.final_exam.model.ProductType;
import com.example.final_exam.repository.IProductRepository;
import com.example.final_exam.repository.IProductTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProductService {
    @Autowired
    private IProductRepository iProductRepository;

    @Autowired
    private IProductTypeRepository iProductTypeRepository;

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return iProductRepository.findAll(pageable);
    }

    @Override
    public void save(Product product) {
        iProductRepository.save(product);
    }

    @Override
    public void deleteById(Integer id) {
        iProductRepository.deleteById(id);
    }

    @Override
    public List<ProductType> findAll() {
        return iProductTypeRepository.findAll();
    }

    @Override
    public Product getById(Integer id) {
        return iProductRepository.getById(id);
    }

    @Override
    public void deleteAllById(List<Integer> productIds) {
        iProductRepository.deleteAllById(productIds);
    }

    @Override
    public Page<Product> searchProducts(String name, ProductType type, Long price, Pageable pageable) {
        return iProductRepository.searchProducts(name, type, price, pageable);
    }

    @Override
    public ProductType findProductTypeByName(String name) {
        return iProductTypeRepository.findByName(name);
    }
}

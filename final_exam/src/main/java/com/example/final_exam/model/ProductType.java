package com.example.final_exam.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class ProductType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cid;
    @Column(unique=true)
    private String name;
    @OneToMany(mappedBy = "productType")
    private List<Product> products;

    public ProductType() {}

    public ProductType(Integer cid, String name, List<Product> products) {
        this.cid = cid;
        this.name = name;
        this.products = products;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}

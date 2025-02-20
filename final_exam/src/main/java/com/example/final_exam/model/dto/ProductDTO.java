package com.example.final_exam.model.dto;

import com.example.final_exam.model.ProductType;
import jakarta.persistence.*;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


public class ProductDTO implements Validator {

    private Integer id;

    private String name;

    private Long price;

    private String status;

    private ProductType productType;

    public ProductDTO() {
    }

    public ProductDTO(Integer id, String name, Long price, String status, ProductType productType) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.status = status;
        this.productType = productType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    @Override
    public String toString() {
        return "ProductDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", status='" + status + '\'' +
                ", productType=" + productType +
                '}';
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        ProductDTO productDTO = (ProductDTO) target;

        String name = productDTO.getName();
        if(name.trim().isEmpty()) {
            errors.rejectValue("name", "input.null");
        } else if (name.length() < 5 || name.length() > 50) {
            errors.rejectValue("name", "","Tên sản phẩm chứa từ 5 đến 50 ký tự");
        }

        Long price = productDTO.getPrice();
        if (price == null) {
            errors.rejectValue("price", "input.null");
        }
        else if (price < 100000) {
            errors.rejectValue("price", "", "Giá bắt đầu thấp nhất phải là 100.000 VND ");
        }
    }
}

package com.example.final_exam.controller;

import com.example.final_exam.model.Product;
import com.example.final_exam.model.ProductType;
import com.example.final_exam.model.dto.ProductDTO;
import com.example.final_exam.service.IProductService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@Controller

public class ProductController {
    @Autowired
    private IProductService iProductService;

    @GetMapping("")
    public String index(Model model) {
        return "redirect:/home";
    }

    @GetMapping("/home")
    public String showHomePage(@RequestParam(value = "page", required = false, defaultValue = "0") int page,
                               @RequestParam(value = "name", required = false, defaultValue = "") String name,
                               @RequestParam(value = "type", required = false, defaultValue = "") String type,
                               @RequestParam(value = "price", required = false, defaultValue = "") Long price,
                               Model model) {
        Pageable pageable = PageRequest.of(page, 5);
        Page<Product> products = iProductService.findAll(pageable);
        if ((name != null && !name.isEmpty()) || type != null || price != null) {
            products = iProductService.searchProducts(name, iProductService.findProductTypeByName(type), price, pageable);
        }
        model.addAttribute("products", products);
        model.addAttribute("productTypes", iProductService.findAll());
        model.addAttribute("name", name);
        model.addAttribute("type", type);
        model.addAttribute("price", price);
        return "home";
    }

    @GetMapping("/create-new")
    public String showCreateNewProductForm(Model model) {
        model.addAttribute("productDTO", new ProductDTO());
        model.addAttribute("productTypes", iProductService.findAll());
        return "create";
    }

    @PostMapping("/add")
    public String addNewProduct(@Valid @ModelAttribute("productDTO") ProductDTO productDTO, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        new ProductDTO().validate(productDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("productTypes", iProductService.findAll());
            model.addAttribute("productDTO", productDTO);
            return "create";
        } else {
            Product product = new Product();
            BeanUtils.copyProperties(productDTO, product);
            if (product.getStatus() == null) {
                product.setStatus("chờ duyệt");
            }
            iProductService.save(product);
            redirectAttributes.addFlashAttribute("message", "Thêm mới thành công!");
            return "redirect:/home";
        }
    }

    @PostMapping("/delete")
    public String deleteProduct(@RequestParam(value = "pids", required = false) List<Integer> pids, RedirectAttributes redirectAttributes) {

        if (pids == null || pids.isEmpty() ) {
            redirectAttributes.addFlashAttribute("error", "Vui lòng chọn sản phẩm để xóa");
        } else {
            iProductService.deleteAllById(pids);
            redirectAttributes.addFlashAttribute("message", "Xóa sản phẩm thành công");
        }
        return "redirect:/home";
    }
}

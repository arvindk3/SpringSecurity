package com.arv.springsecurity.controller;

import com.arv.springsecurity.dto.Product;
import com.arv.springsecurity.entity.ProductInfo;
import com.arv.springsecurity.entity.UserInfo;
import com.arv.springsecurity.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome this endpoint is not secure";
    }

    @PostMapping("/new")
    public String addNewUser(@RequestBody UserInfo userInfo){
        return service.addUser(userInfo);
    }

    @PostMapping("/addProduct")
    public String addNewProduct(@RequestBody ProductInfo productInfo){
        return service.addProducts(productInfo);
    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<ProductInfo> getAllTheProducts() {

        return service.getProducts();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ProductInfo getProductById(@PathVariable int id) {
        return service.getProduct(id);
    }
}
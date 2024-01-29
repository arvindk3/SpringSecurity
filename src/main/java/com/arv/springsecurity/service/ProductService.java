package com.arv.springsecurity.service;

import com.arv.springsecurity.dto.Product;
import com.arv.springsecurity.entity.ProductInfo;
import com.arv.springsecurity.entity.UserInfo;
import com.arv.springsecurity.repository.ProductInfoRepository;
import com.arv.springsecurity.repository.UserInfoRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class ProductService {


    @Autowired
    private UserInfoRepository repository;
    @Autowired
    private ProductInfoRepository pRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;



    public List<ProductInfo> getProducts() {
        return pRepository.findAll();
    }

   public ProductInfo getProduct(int id) {
       return pRepository.findById(id).stream()
               .filter(product -> product.getProductId() == id)
               .findAny()
               .orElseThrow(() -> new RuntimeException("product " + id + " not found"));
   }

    public String addUser(UserInfo userInfo) {
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        repository.save(userInfo);
        return "user added to the system successfully ";
    }

    public String addProducts(ProductInfo productInfo) {
        pRepository.save(productInfo);
        return "Product added successfully ";
    }
}
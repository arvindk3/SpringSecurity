package com.arv.springsecurity.repository;

import com.arv.springsecurity.entity.ProductInfo;
import com.arv.springsecurity.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductInfoRepository extends JpaRepository<ProductInfo, Integer> {

    Optional<ProductInfo> findByName(String productName);

    @Override
    List<ProductInfo> findAll();
}

package com.example.design.repo;

import com.example.design.pojo.ProductItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductItemRepository  extends JpaRepository<ProductItem,Integer> {

}

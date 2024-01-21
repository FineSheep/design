package com.example.design.controller;

import com.example.design.combination.ProductComposite;
import com.example.design.service.ProductItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("product")
public class ProductController {
    @Autowired
    private ProductItemService productItemService;

    @GetMapping("findAll")
    public ProductComposite findAll() {
        ProductComposite composite = productItemService.fetchAllItems();
        return composite;
    }
}

package com.example.design.service;

import com.example.design.combination.AbstractProductItem;
import com.example.design.combination.ProductComposite;
import com.example.design.pojo.ProductItem;
import com.example.design.repo.ProductItemRepository;
import com.example.design.utils.RedisCommonProcessor;
import com.example.design.visitor.AddItemVisitor;
import com.example.design.visitor.DeleteVisitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProductItemService {
    @Autowired
    private RedisCommonProcessor redisProcessor;
    @Autowired
    private ProductItemRepository productItemRepository;

    @Autowired
    private AddItemVisitor addItemVisitor;
    @Autowired
    private DeleteVisitor deleteVisitor;

    public ProductComposite fetchAllItems() {
        Object cacheItems = redisProcessor.get("items");
        if (cacheItems != null) {
            return (ProductComposite) cacheItems;
        }
        List<ProductItem> fetchDbItems = productItemRepository.findAll();
        ProductComposite composite = generateProductTree(fetchDbItems);
        if (composite == null) {
            throw new UnsupportedOperationException("no support");
        }
        redisProcessor.set("item", composite);
        return composite;
    }

    private ProductComposite generateProductTree(List<ProductItem> fetchDbItems) {
        ArrayList<ProductComposite> composites = new ArrayList<>(fetchDbItems.size());

        fetchDbItems.forEach(dbitem -> composites.add(ProductComposite.builder()
                .id(dbitem.getId())
                .name(dbitem.getName())
                .pid(dbitem.getPid())
                .build()));
        Map<Integer, List<ProductComposite>> groupingList = composites.stream().collect(Collectors.groupingBy(ProductComposite::getPid));
        composites.forEach(item -> {
            List<ProductComposite> list = groupingList.get(item.getId());
            item.setChild(list == null ? new ArrayList<>() : list.stream().map(x -> (AbstractProductItem) x).collect(Collectors.toList()));
        });
        ProductComposite composite = composites.size() == 0 ? null : composites.get(0);
        return composite;
    }

    public ProductComposite addItems(ProductItem item) {
        productItemRepository.addItem(item.getName(), item.getPid());
        ProductComposite addItem = ProductComposite.builder().id(productItemRepository.findByNameAndPid(item.getName(), item.getId()).getId())
                .name(item.getName())
                .pid(item.getPid())
                .child(new ArrayList<>())
                .build();

        AbstractProductItem updateItems = addItemVisitor.visitor(addItem);

        redisProcessor.set("items", updateItems);
        return (ProductComposite) updateItems;
    }

    public ProductComposite delIterm(ProductItem item) {
        productItemRepository.delItem(item.getId());

        ProductComposite delItem = ProductComposite.builder()
                .id(item.getId())
                .name(item.getName())
                .pid(item.getPid())
                .build();
        AbstractProductItem updateItems = deleteVisitor.visitor(delItem);
        redisProcessor.set("items", updateItems);
        return (ProductComposite) updateItems;

    }
}

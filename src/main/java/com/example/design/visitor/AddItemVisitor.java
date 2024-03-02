package com.example.design.visitor;

import com.example.design.combination.AbstractProductItem;
import com.example.design.combination.ProductComposite;
import com.example.design.utils.RedisCommonProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author yanghao
 * @createTime 2024/1/27 16:54
 * @description
 */

@Repository
public class AddItemVisitor implements ItemVisitor<AbstractProductItem> {

    @Autowired
    private RedisCommonProcessor redisCommonProcessor;

    @Override
    public AbstractProductItem visitor(AbstractProductItem productItem) {
        ProductComposite currentItem = (ProductComposite) redisCommonProcessor.get("items");
        ProductComposite additem = (ProductComposite) productItem;
        if (additem.getPid() == currentItem.getId()) {
            currentItem.addProductItem(additem);
            return currentItem;
        }
        addChild(additem, currentItem);
        return currentItem;
    }

    private void addChild(ProductComposite addItem, ProductComposite currentItem) {
        List<AbstractProductItem> child = currentItem.getChild();
        for (AbstractProductItem abstractProductItem : child) {
            ProductComposite item = (ProductComposite) abstractProductItem;
            if (item.getId() == addItem.getPid()) {
                item.addProductItem(addItem);
                break;
            } else {
                addChild(addItem, item);
            }
        }
    }
}

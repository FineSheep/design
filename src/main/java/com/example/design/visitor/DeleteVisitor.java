package com.example.design.visitor;

import com.example.design.combination.AbstractProductItem;
import com.example.design.combination.ProductComposite;
import com.example.design.utils.RedisCommonProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author yanghao
 * @createTime 2024/1/27 17:09
 * @description
 */
@Repository
public class DeleteVisitor implements ItemVisitor<AbstractProductItem> {

    @Autowired
    private RedisCommonProcessor redisCommonProcessor;

    @Override
    public AbstractProductItem visitor(AbstractProductItem productItem) {
        ProductComposite currentItem = (ProductComposite) redisCommonProcessor.get("items");
        ProductComposite delItem = (ProductComposite) productItem;
        if (delItem.getId() == currentItem.getId()){
            throw new UnsupportedOperationException("root cant not be deleted !");
        }
        if (delItem.getPid() == currentItem.getPid()){
            currentItem.delProductItem(delItem);
            return currentItem;
        }
        delChild(delItem,currentItem);
        return currentItem;
    }


    private void delChild(ProductComposite productItem, ProductComposite currentItem) {
        for (AbstractProductItem abstractProductItem : currentItem.getChild()) {
            ProductComposite item = (ProductComposite) abstractProductItem;
            if (item.getId() == productItem.getPid()) {
                item.delProductItem(productItem);
                break;
            } else {
                delChild(productItem, item);
            }
        }
    }
}

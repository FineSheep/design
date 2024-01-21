package com.example.design.combination;

public abstract class AbstractProductItem {
    //add product
    protected void addProductItem(AbstractProductItem item){
        throw new UnsupportedOperationException("not support child add");
    }

    //del product
    protected void adelProductItem(AbstractProductItem item){
        throw new UnsupportedOperationException("not support child delete");
    }
}

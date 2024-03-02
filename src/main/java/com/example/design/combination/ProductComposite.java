package com.example.design.combination;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductComposite extends AbstractProductItem implements Serializable {
    private int id;
    private String name;
    private int pid;
    private List<AbstractProductItem> child = new ArrayList<>();

    @Override
    public void addProductItem(AbstractProductItem item) {
        this.child.add(item);
    }

    @Override
    public void delProductItem(AbstractProductItem item) {
        ProductComposite removeItem = (ProductComposite) item;
        Iterator<AbstractProductItem> iterator = child.iterator();
        while (iterator.hasNext()) {
            ProductComposite composite = (ProductComposite) iterator.next();

            if (composite.getId() == removeItem.getId()) {
                iterator.remove();
                break;
            }
        }
    }
}

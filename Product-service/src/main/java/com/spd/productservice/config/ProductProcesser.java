package com.spd.productservice.config;

import com.spd.productservice.entity.Product;
import org.springframework.batch.item.ItemProcessor;

public class ProductProcesser implements ItemProcessor<Product, Product> {
    @Override
    public Product process(Product item) throws Exception {
        return item;
    }
}

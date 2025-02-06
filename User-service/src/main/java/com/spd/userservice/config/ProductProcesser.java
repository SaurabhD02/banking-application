package com.spd.userservice.config;

import com.spd.userservice.entity.Product;
import org.springframework.batch.item.ItemProcessor;

public class ProductProcesser implements ItemProcessor<Product, Product> {
    @Override
    public Product process(Product item) throws Exception {
        return item;
    }
}

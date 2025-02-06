package com.spd.productservice.entity;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;

@Data
public class ProductMappingMaster {

    @CsvBindByName(column = "PRODUCT_ID")
    private String productId;
    @CsvBindByName(column = "NAME")
    private String name;
    @CsvBindByName(column = "PRICE")
    private double price;
    @CsvBindByName(column = "QUANTITY")
    private int quantity;
    @CsvBindByName(column = "DESCRIPTION")
    private String description;
    @CsvBindByName(column = "CATEGORY_ID")
    private String categoryId;
}

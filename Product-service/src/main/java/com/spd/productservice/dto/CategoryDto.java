package com.spd.productservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.spd.productservice.entity.Product;


import java.util.Set;

public class CategoryDto {

    @JsonProperty("iCategoryId")
    private Integer categoryId;
    @JsonProperty("sCategoryTitle")
    private String categoryTitle;
    @JsonProperty("sProducts")
    private Set<Product> products;
}

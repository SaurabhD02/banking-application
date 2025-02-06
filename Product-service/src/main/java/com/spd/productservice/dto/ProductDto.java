package com.spd.productservice.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.spd.productservice.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductDto {

    @JsonProperty("sProductId")
    private String productId;
    @JsonProperty("sName")
    private String name;
    @JsonProperty("dPrice")
    private double price;
    @JsonProperty("iQuantity")
    private int quantity;
    @JsonProperty("sDescription")
    private String description;
    @JsonProperty("sCategory")
    private Category category;
}

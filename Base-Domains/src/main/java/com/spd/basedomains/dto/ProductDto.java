package com.spd.basedomains.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
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
}

package com.spd.productservice.service.impl;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import com.spd.productservice.dto.ProductDto;
import com.spd.productservice.entity.Product;
import com.spd.productservice.entity.ProductMappingMaster;
import com.spd.productservice.exception.ProductNotFoundException;
import com.spd.productservice.repository.ProductRepository;
import com.spd.productservice.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ModelMapper modelMapper;
    private final ProductRepository productRepository;

    @Override
    public ProductDto addProduct(ProductDto productDto) {
        Product product = modelMapper.map(productDto, Product.class);
        Product savedProduct = productRepository.save(product);
        return modelMapper.map(savedProduct, ProductDto.class);
    }

    @Override
    public ProductDto updateProduct(String productId, ProductDto productDto) {
        Product product = productRepository.findByProductId(productId).orElse(null);
        if(product != null) {
            product.setName(productDto.getName());
            product.setDescription(productDto.getDescription());
            product.setPrice(productDto.getPrice());
            product.setQuantity(productDto.getQuantity());
            Product updatedProduct = productRepository.save(product);
            return modelMapper.map(updatedProduct, ProductDto.class);
        }else{
            log.error("Exception occurred while updating productwith id : {}", productId);
            throw new ProductNotFoundException("Product not found with id: " + productId);
        }

    }

    @Override
    public ProductDto getProduct(String productId) {
        Product product = productRepository.findByProductId(productId).orElse(null);
        if(product != null) {
            return modelMapper.map(product, ProductDto.class);
        }else{
            log.error("Exception occurred while fetching product with id : {}", productId);
            throw new ProductNotFoundException("Product not found with id: " + productId);
        }
    }

    @Override
    public List<ProductDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(product -> modelMapper.map(product, ProductDto.class)).collect(Collectors.toList());
    }

    @Override
    public void deleteProduct(String productId) {
        productRepository.deleteById(productId);
    }

    @Override
    public String uploadMaster(MultipartFile file) {
        if(file.getContentType().equalsIgnoreCase("txt/csv")){
            return "Invalid file";
        }else {
            Instant startTime = Instant.now();
            List<Product> products = parseCsv(file);
            productRepository.saveAll(products);
            Instant endTime = Instant.now();
            Duration duration = Duration.between(startTime, endTime);
            log.info("time taken to saved all products is : {}", duration.toMillis());
            return "Products saved successfully of size : " + products.size();
        }
    }

    private List<Product> parseCsv(MultipartFile file) {

        try(Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))){

            HeaderColumnNameMappingStrategy<ProductMappingMaster> strategy =
                    new HeaderColumnNameMappingStrategy<>();
            strategy.setType(ProductMappingMaster.class);
            CsvToBean<ProductMappingMaster> csvToBean = new CsvToBeanBuilder<ProductMappingMaster>(reader)
                    .withMappingStrategy(strategy)
                    .withIgnoreEmptyLine(true)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
           return csvToBean.parse().stream().map(csvLine-> Product.builder()
                    .price(csvLine.getPrice())
                    .name(csvLine.getName())
                    .description(csvLine.getDescription())
                    .categoryId(csvLine.getCategoryId())
                    .quantity(csvLine.getQuantity())
                    .productId(csvLine.getProductId())
                    .build()).toList();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

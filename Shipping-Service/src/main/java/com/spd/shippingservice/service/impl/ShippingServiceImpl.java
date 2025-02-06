package com.spd.shippingservice.service.impl;

import com.spd.basedomains.dto.OrderDto;
import com.spd.basedomains.dto.ProductDto;
import com.spd.basedomains.dto.UserDto;
import com.spd.shippingservice.dto.OrderItemDto;
import com.spd.shippingservice.entity.OrderItem;
import com.spd.shippingservice.entity.OrderItemId;
import com.spd.shippingservice.exception.OrderItemNotFoundException;
import com.spd.shippingservice.repository.ShippingServiceRepository;
import com.spd.shippingservice.service.ShippingService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class ShippingServiceImpl implements ShippingService {

    private ShippingServiceRepository shippingServiceRepository;
    private ModelMapper modelMapper;
    private RestTemplate restTemplate;


    @Override
    public List<OrderItemDto> findAll() {
        log.info("*** OrderItemDto List, serviceImpl; fetch all orderItems *");
        List<OrderItem>  orderItems = shippingServiceRepository.findAll();
        return orderItems.stream().map(orderItem -> modelMapper.map(orderItem, OrderItemDto.class))
                .map(f->{
                    ProductDto productDto = restTemplate.getForObject("http://localhost:8084/api/products/"+ f.getProductId(), ProductDto.class);
                f.setProductDto(productDto);
                    OrderDto orderDto = restTemplate.getForObject("http://localhost:8084/api/orders/"+ f.getOrderId(), OrderDto.class);
                    f.setOrderDto(orderDto);
                return f;})
                .toList();
    }

    @Override
    public OrderItemDto findById(OrderItemId orderItemId) {
        log.info("*** OrderItemDto , serviceImpl; fetch orderItem with orderItemId *");
        OrderItem orderItem = shippingServiceRepository.findById(orderItemId).orElseThrow(()->new OrderItemNotFoundException("OrderItem not found with orderId "+ orderItemId));
        OrderItemDto orderItemDto = modelMapper.map(orderItem, OrderItemDto.class);
        ProductDto productDto = restTemplate.getForObject("http://localhost:8084/api/products/"+ orderItemDto.getProductId(), ProductDto.class);
        OrderDto orderDto = restTemplate.getForObject("http://localhost:8084/api/orders/"+ orderItemDto.getOrderId(), OrderDto.class);
        orderItemDto.setProductDto(productDto);
        orderItemDto.setOrderDto(orderDto);
        return orderItemDto;
    }

    @Override
    public OrderItemDto save(OrderItemDto orderItemDto) {
        log.info("*** OrderItemDto , serviceImpl; save orderItem *");
        OrderItem orderItem = modelMapper.map(orderItemDto, OrderItem.class);
        OrderItem savedOrderItem = shippingServiceRepository.save(orderItem);
        return modelMapper.map(savedOrderItem, OrderItemDto.class);
    }

    @Override
    public OrderItemDto update(OrderItemDto orderItemDto) {
        log.info("*** OrderItemDto , serviceImpl; update orderItem *");
        OrderItem orderItem = modelMapper.map(orderItemDto, OrderItem.class);
        OrderItem updatedOrderItem = shippingServiceRepository.save(orderItem);
        return modelMapper.map(updatedOrderItem, OrderItemDto.class);
    }

    @Override
    public void deleteById(OrderItemId orderItemId) {
        log.info("*** OrderItemDto , serviceImpl; delete orderItem with orderItemId *");
        shippingServiceRepository.deleteById(orderItemId);
    }
}

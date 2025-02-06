package com.spd.orderservice.service.impl;

import com.spd.orderservice.dto.OrderDto;
import com.spd.orderservice.entity.Order;
import com.spd.orderservice.exception.OrderNotFoundException;
import com.spd.orderservice.repository.OrderRepository;
import com.spd.orderservice.service.OrderService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;
    private ModelMapper modelMapper;

    @Override
    public List<OrderDto> findAll() {
        log.info("*** OrderDto List, service; fetch all orders *");
        List<Order> orders = orderRepository.findAll();
        return orders.stream().map(order -> modelMapper.map(order, OrderDto.class)).toList();
    }

    @Override
    public OrderDto findById(Integer orderId) {
        log.info("*** OrderDto , service; fetch order by orderId *");
        Order order = orderRepository.findById(orderId).orElseThrow(()-> new OrderNotFoundException("Order not found with orderId -"+ orderId));
        return modelMapper.map(order, OrderDto.class);
    }

    @Override
    public OrderDto save(OrderDto orderDto) {
        log.info("*** OrderDto , service; save order *");
        Order order = modelMapper.map(orderDto, Order.class);
        Order savedOrder = orderRepository.save(order);
        return modelMapper.map(savedOrder, OrderDto.class);
    }

    @Override
    public OrderDto update(OrderDto orderDto) {
        log.info("*** OrderDto , service; save order *");
        Order order = modelMapper.map(orderDto, Order.class);
        Order updatedOrder = orderRepository.save(order);
        return modelMapper.map(updatedOrder, OrderDto.class);
    }

    @Override
    public OrderDto update(Integer orderId, OrderDto orderDto) {
        return null;
    }

    @Override
    public void deleteById(Integer orderId) {
        log.info("*** OrderDto , service; delete order by orderId *");
        orderRepository.deleteById(orderId);
    }
}

package com.spd.orderservice.service.impl;

import com.spd.basedomains.dto.UserDto;
import com.spd.orderservice.dto.CartDto;
import com.spd.orderservice.entity.Cart;
import com.spd.orderservice.exception.CartNotFoundException;
import com.spd.orderservice.exception.OrderNotFoundException;
import com.spd.orderservice.repository.CartRepository;
import com.spd.orderservice.service.CartService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class CartServiceImpl implements CartService {

    private CartRepository cartRepository;
    private ModelMapper modelMapper;
    private RestTemplate restTemplate;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<CartDto> findAll() {
        log.info("*** CartDto List, service; fetch all carts *");
        List<Cart> carts = cartRepository.findAll();
        return carts.stream().map(x -> modelMapper.map(x, CartDto.class))
                .map(f->{
                        UserDto userDto = restTemplate.getForObject("http://localhost:8084/api/users/"+ f.getUserName(), UserDto.class);
                f.setUserDto(userDto);
                    return f;
                })
                .toList();
    }

    @Override
    public CartDto findById(Integer cartId) {
        log.info("*** CartDto , service; fetch by cartId *");
        CartDto cartDto = cartRepository.findById(cartId).map(x -> modelMapper.map(x, CartDto.class))
                .map(f->{
                    UserDto userDto = restTemplate.getForObject("http://localhost:8084/api/users/"+ f.getUserName(), UserDto.class);
                    f.setUserDto(userDto);
                    return f;}).orElseThrow(()->  new CartNotFoundException("Cart not found with id : "+ cartId));
        CompletableFuture<UserDto> userDtoCompletableFuture = CompletableFuture.supplyAsync(()-> restTemplate.getForObject("http://localhost:8084/api/users/"+ cartDto.getUserName(), UserDto.class));
        return cartDto;
    }

    @Override
    public CartDto save(CartDto cartDto) {
        log.info("*** CartDto , service; save by cart *");
        Cart cart = modelMapper.map(cartDto, Cart.class);
        Cart savedCart = cartRepository.save(cart);
        return modelMapper.map(savedCart, CartDto.class);
    }

    @Override
    public CartDto update(CartDto cartDto) {
        log.info("*** CartDto , service; update  cart *");
        Cart cart = modelMapper.map(cartDto, Cart.class);
        Cart updatedCart = cartRepository.save(cart);
        return modelMapper.map(updatedCart, CartDto.class);
    }

    @Override
    public CartDto update(Integer cartId, CartDto cartDto) {
//        log.info("*** CartDto , service; update  cart *");
//        return modelMapper.map(updatedCart, CartDto.class);
        return null;
    }

    @Override
    public void deleteById(Integer cartId) {
        log.info("*** CartDto , service; delete cart by cartId*");
        cartRepository.deleteById(cartId);
    }
}

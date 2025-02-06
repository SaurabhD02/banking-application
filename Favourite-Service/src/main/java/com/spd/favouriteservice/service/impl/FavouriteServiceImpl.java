package com.spd.favouriteservice.service.impl;

import com.spd.basedomains.dto.ProductDto;
import com.spd.basedomains.dto.UserDto;
import com.spd.favouriteservice.dto.FavouriteDto;
import com.spd.favouriteservice.entity.Favourite;
import com.spd.favouriteservice.entity.FavouriteId;
import com.spd.favouriteservice.repository.FavouriteRepository;
import com.spd.favouriteservice.service.FavouriteService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class FavouriteServiceImpl implements FavouriteService {

    private ModelMapper modelMapper;
    private FavouriteRepository favouriteRepository;
    private RestTemplate restTemplate;

    @Override
    public List<FavouriteDto> findAll() {
        log.info("*** FavouriteDto List, serviceImpl; fetch favourites *");
        List<Favourite> favourites = favouriteRepository.findAll();
        return favourites.stream().map(x-> modelMapper.map(x, FavouriteDto.class)).map( f->{
            ProductDto productDto = this.restTemplate.getForObject("http://localhost:8083/api/products"+"/"+f.getProductId(), ProductDto.class);
            UserDto userDto = this.restTemplate.getForObject("http://localhost:8084/api/users"+"/"+f.getUserId(), UserDto.class);
            f.setProductDto(productDto);
            f.setUserDto(userDto);
            return f;
        }).distinct().toList();
    }

    @Override
    public FavouriteDto findById(FavouriteId id) {
        log.info("*** FavouriteDto , serviceImpl; fetch favourite by favouriteId *");
        Favourite favourite = favouriteRepository.findById(id).orElse(null);
        return modelMapper.map(favourite, FavouriteDto.class);
    }

    @Override
    public FavouriteDto save(FavouriteDto favouriteDto) {
        log.info("*** FavouriteDto , serviceImpl; save favourite *");
        Favourite favourite = modelMapper.map(favouriteDto, Favourite.class);
        Favourite savedFavourite = favouriteRepository.save(favourite);
        return modelMapper.map(savedFavourite, FavouriteDto.class);
    }

    @Override
    public FavouriteDto update(FavouriteDto favouriteDto) {
        log.info("*** FavouriteDto , serviceImpl; update favourite *");
        Favourite favourite = modelMapper.map(favouriteDto, Favourite.class);
        Favourite updatedFavourite = favouriteRepository.save(favourite);
        return modelMapper.map(updatedFavourite, FavouriteDto.class);
    }

    @Override
    public void delete(FavouriteId favouriteId) {
        log.info("*** FavouriteDto , serviceImpl; delete favourite by favouriteId *");
        favouriteRepository.deleteById(favouriteId);
    }
}

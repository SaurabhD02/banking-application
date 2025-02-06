package com.spd.favouriteservice.service;

import com.spd.favouriteservice.dto.FavouriteDto;
import com.spd.favouriteservice.entity.FavouriteId;

import java.util.List;

public interface FavouriteService {

    public List<FavouriteDto> findAll();
    public FavouriteDto findById(FavouriteId id);
    public FavouriteDto save(FavouriteDto favouriteDto);
    public FavouriteDto update(FavouriteDto favouriteDto);
    public void delete(FavouriteId favouriteId);
}

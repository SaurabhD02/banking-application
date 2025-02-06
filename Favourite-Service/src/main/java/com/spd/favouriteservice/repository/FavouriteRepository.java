package com.spd.favouriteservice.repository;

import com.spd.favouriteservice.entity.Favourite;
import com.spd.favouriteservice.entity.FavouriteId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavouriteRepository extends JpaRepository<Favourite, FavouriteId> {
}

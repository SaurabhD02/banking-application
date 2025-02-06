package com.spd.favouriteservice.controller;

import com.spd.favouriteservice.dto.FavouriteDto;
import com.spd.favouriteservice.entity.Favourite;
import com.spd.favouriteservice.entity.FavouriteId;
import com.spd.favouriteservice.service.FavouriteService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favourites")
@AllArgsConstructor
public class FavouriteController {

    private FavouriteService favouriteService;

    @PostMapping
    public ResponseEntity<FavouriteDto> addFavourite(@RequestBody FavouriteDto favouriteDto) {
        return new ResponseEntity<>(favouriteService.save(favouriteDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<FavouriteDto>> getAllFavourites() {
        return new ResponseEntity<>(favouriteService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{favouriteId}")
    public ResponseEntity<FavouriteDto> getFavouriteById(@PathVariable FavouriteId favouriteId) {
        return new ResponseEntity<>(favouriteService.findById(favouriteId), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<FavouriteDto> updateFavourite(@RequestBody FavouriteDto favouriteDto) {
        return new ResponseEntity<>(favouriteService.update(favouriteDto), HttpStatus.OK);
    }

    @DeleteMapping("/{favouriteId}")
    public ResponseEntity<?> deleteFavourite(@PathVariable FavouriteId favouriteId) {
        favouriteService.delete(favouriteId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

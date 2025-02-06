package com.spd.favouriteservice.dto;

import com.spd.basedomains.dto.ProductDto;
import com.spd.basedomains.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FavouriteDto {

    private Integer userId;
    private Integer productId;
    private LocalDateTime likeDate;
    private UserDto userDto;
    private ProductDto productDto;
}

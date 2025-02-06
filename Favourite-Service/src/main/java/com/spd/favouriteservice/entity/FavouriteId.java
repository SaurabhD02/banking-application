package com.spd.favouriteservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FavouriteId implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Integer userId;

	private Integer productId;

}











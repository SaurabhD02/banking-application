package com.spd.userservice.service;

import com.spd.userservice.dto.AddressDto;

import java.util.List;

public interface AddressService {

    public AddressDto addAddress(AddressDto addressDto);
    public AddressDto updateAddress(AddressDto addressDto);
    public AddressDto updateAddress(int id, AddressDto addressDto);
    public AddressDto getAddress(int addressId);
    public List<AddressDto> getAllAddress(String userName);
    public void deleteAddress(int addressId);
}

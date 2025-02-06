package com.spd.userservice.service.impl;

import com.spd.userservice.dto.AddressDto;
import com.spd.userservice.entity.Address;
import com.spd.userservice.exception.AddressNotFoundException;
import com.spd.userservice.repository.AddressRepository;
import com.spd.userservice.service.AddressService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class AddressServiceImpl implements AddressService {

    private AddressRepository addressRepository;
    private ModelMapper modelMapper;

    @Override
    public AddressDto addAddress(AddressDto addressDto) {
        log.info("*** AddressDto , serviceImpl; add address *");
        Address address = modelMapper.map(addressDto, Address.class);
        Address savedAddress = addressRepository.save(address);
        return modelMapper.map(savedAddress, AddressDto.class);
    }

    @Override
    public AddressDto updateAddress(AddressDto addressDto) {
        log.info("*** AddressDto , serviceImpl; update address *");
        Address address = modelMapper.map(addressDto, Address.class);
        Address updatedAddress = addressRepository.save(address);
        return modelMapper.map(updatedAddress, AddressDto.class);
    }

    @Override
    public AddressDto updateAddress(int id, AddressDto addressDto) {
        log.info("*** AddressDto , serviceImpl; update address by addressId *");
        Address address = addressRepository.findById(id).orElse(null);
        if(address != null) {
            address.setAddressType(addressDto.getAddressType());
            address.setCity(addressDto.getCity());
            address.setCountry(addressDto.getCountry());
            address.setStreet(addressDto.getStreet());
            address.setZip(addressDto.getZip());
            address.setState(addressDto.getState());
            Address updatedAddress = addressRepository.save(address);
            return modelMapper.map(updatedAddress, AddressDto.class);
        }else{
            log.error("Address not found with id : {}" , id);
            throw new AddressNotFoundException("Address not found with id : "+ id);
        }
    }

    @Override
    public AddressDto getAddress(int addressId) {
        log.info("*** AddressDto , serviceImpl; get address by addressId *");
        Address address = addressRepository.findById(addressId).orElse(null);
        if(address != null) {
            return modelMapper.map(address, AddressDto.class);
        }else{
            log.error("Address not found with id : {}" , addressId);
            throw new AddressNotFoundException("Address not found with id : "+ addressId);
        }
    }

    @Override
    public List<AddressDto> getAllAddress(String userName) {
        log.info("*** AddressDto List , serviceImpl; fetch  addresses based on username *");
       List<Address> addressList = addressRepository.findAddressByUsername(userName);
       return addressList.stream().map(address -> modelMapper.map(address, AddressDto.class)).collect(Collectors.toList());
    }

    @Override
    public void deleteAddress(int addressId) {
        log.info("*** AddressDto , serviceImpl; delete address by addressId *");
        Address address = addressRepository.findById(addressId).orElse(null);
        if(address != null) {
            addressRepository.delete(address);
        }else{
            log.error("Address not found with id : {}" , addressId);
            throw new AddressNotFoundException("Address not found with id : "+ addressId);
        }
    }
}

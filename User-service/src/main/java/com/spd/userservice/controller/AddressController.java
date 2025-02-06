package com.spd.userservice.controller;

import com.spd.userservice.dto.AddressDto;
import com.spd.userservice.service.AddressService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/addresses")
@AllArgsConstructor
@Slf4j
public class AddressController {

    private final AddressService addressService;

    @PostMapping
    public ResponseEntity<AddressDto> addAddress(@RequestBody AddressDto addressDto) {
        log.info("*** AddressDto , controller; add address *");
        return new ResponseEntity<>(addressService.addAddress(addressDto), HttpStatus.OK);
    }

    @GetMapping("/{addressId}")
    public ResponseEntity<AddressDto> getAddress(@PathVariable("addressId") int addressId) {
        log.info("*** AddressDto , controller; get address by addressId *");
        return new ResponseEntity<>(addressService.getAddress(addressId), HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<AddressDto> updateAddress(@RequestBody AddressDto addressDto) {
        log.info("*** AddressDto , controller; update address *");
        return new ResponseEntity<>(addressService.updateAddress(addressDto), HttpStatus.OK);
    }

    @PutMapping("/{addressId}")
    public ResponseEntity<AddressDto> updateAddress(@PathVariable("addressId") int addressId, @RequestBody AddressDto address) {
        log.info("*** AddressDto , controller; update address by addressId *");
        return new ResponseEntity<>(addressService.updateAddress(addressId, address), HttpStatus.OK);
    }
    @DeleteMapping("/{addressId}")
    public ResponseEntity<?> deleteAddress(@PathVariable("addressId") int addressId) {
        log.info("*** AddressDto , controller; delete address by addressId *");
        addressService.deleteAddress(addressId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<AddressDto>> getAddressByUsername(@RequestParam("username") String username) {
        log.info("*** AddressDto List , controller; fetch  addresses based on username *");
        return new ResponseEntity<>(addressService.getAllAddress(username), HttpStatus.OK);
    }
}

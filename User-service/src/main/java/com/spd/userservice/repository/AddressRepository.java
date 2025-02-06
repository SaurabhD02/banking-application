package com.spd.userservice.repository;

import com.spd.userservice.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Integer> {

    @Query("select a from Address a where a.user.username=:userName")
    List<Address> findAddressByUsername(@Param("userName") String userName);
}

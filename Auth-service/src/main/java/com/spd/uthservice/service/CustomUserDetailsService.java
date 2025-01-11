package com.spd.uthservice.service;

import com.spd.uthservice.entity.UserEntity;
import com.spd.uthservice.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<UserEntity> optionalUser = userRepository.findByUserName(username);
        if(optionalUser.isPresent()) {
            UserEntity userEntity = optionalUser.get();
            Set<GrantedAuthority> authorities = userEntity.getRoles().stream()
                    .map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toSet());

            return User.builder()
                    .username(userEntity.getUserName())
                    .password(userEntity.getPassword())
                    .authorities(authorities)
                    .build();

        }else{
            throw new UsernameNotFoundException(username);
        }
    }
}

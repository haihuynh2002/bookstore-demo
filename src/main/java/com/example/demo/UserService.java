/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

/**
 *
 * @author infoh
 */
@Service
@Transactional
public class UserService {
    
    @Autowired
    UserRepository ur;
    
    @Autowired
    RoleRepository rr;
    
    @Autowired
    CartRepository cr;
    
    @Autowired
    PasswordEncoder pe;

    
    public User createUser(UserDto userDto) {
        ur.findByEmail(userDto.getEmail()).ifPresent(user -> {
            throw new RuntimeException("user exists");
        });
        
        Role role = rr.findByName("USER").orElseGet(Role::new);
        rr.save(role);
        
        Cart cart = new Cart();
        
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.addRole(role);
        user.addCart(cart);
        user.setPassword(pe.encode(userDto.getPassword()));
        return ur.save(user);
    }

}

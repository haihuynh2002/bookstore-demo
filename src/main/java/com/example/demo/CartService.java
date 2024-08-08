/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author infoh
 */
@Service
@Transactional
public class CartService {
    
    @Autowired
    CartRepository cr;
    
    public List<CartBook> findCartBooks(Authentication auth) {
        List<CartBook> cartBooks = cr.findByEmail(auth.getName()).get().getCartBooks();
        cartBooks.forEach(line -> System.out.println(line.getBook().getTitle()));
        return cartBooks;
    }
    
}

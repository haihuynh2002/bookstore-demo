/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author infoh
 */
@Controller
@RequestMapping("/cart")
public class CartController {
    
    @Autowired
    CartRepository cr;
    
    @Autowired
    CartService cs;
    
    @GetMapping
    public String getCart(Authentication auth, Model model) {
        List<CartBook> cartBooks = cs.findCartBooks(auth);
        model.addAttribute("cartBooks", cartBooks);
        return "cart";
    }
}

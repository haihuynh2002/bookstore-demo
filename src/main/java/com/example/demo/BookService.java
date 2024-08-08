/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo;

import java.util.List;
import org.springframework.beans.BeanUtils;
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
public class BookService {

    @Autowired
    BookRepository br;

    @Autowired
    UserRepository ur;

    @Autowired
    CartRepository cr;

    @Autowired
    UserService us;

    public Book findById(Long id) {
        return br.findById(id).orElseThrow(()
                -> new RuntimeException("Book not found")
        );
    }

    public CartBook addCart(Authentication auth, CartDto cartDto) {
        var book = br.findById(cartDto.getBookId()).orElseThrow(() -> new RuntimeException("book not found"));
        var cart = cr.findByEmail(auth.getName()).orElseThrow(() -> new RuntimeException("cart not found"));

        CartBookKey key = new CartBookKey(cart.getId(), book.getId());
        CartBook cb = new CartBook();
        cb.setId(key);
        cb.addCartBook(cart, book);
        return cb;
    }

    public Book createBook(BookDto bookDto) {
        Book book = new Book();
        BeanUtils.copyProperties(bookDto, book);
        return br.save(book);
    }

    public List<Book> findAll() {
        return br.findAll();
    }
}

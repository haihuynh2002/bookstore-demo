/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo;

import static com.example.demo.MainController.UPLOAD_DIRECTORY;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author infoh
 */
@Controller
@RequestMapping("/books")
public class BookController {

    @Autowired
    BookService bs;

    @GetMapping
    public String getBooks(Model model) {
        List<Book> books = bs.findAll();
        model.addAttribute("books", books);
        return "books";
    }

    @GetMapping("/{id}")
    public String getBook(@PathVariable("id") Long id, Model model) {
        Book book = bs.findById(id);
        CartDto cartDto = new CartDto();
        cartDto.setBookId(id);
        model.addAttribute("book", book);
        model.addAttribute("cart", cartDto);
        return "book";
    }

    @PostMapping("/addCart")
    public String postBook(@ModelAttribute("cart") CartDto cartDto,
            Authentication auth) {
        bs.addCart(auth, cartDto);
        return "redirect:/books";
    }

    @GetMapping("/addBook")
    public String getAddBook(Model model) {
        BookDto bookDto = new BookDto();
        model.addAttribute("book", bookDto);
        return "addBook";
    }

    @PostMapping("/addBook")
    public String postAddBook(@ModelAttribute("book") BookDto bookDto,
            @RequestParam("image") MultipartFile file, Model model) throws IOException {
        bs.createBook(bookDto);
        return "redirect:/";
    }
}

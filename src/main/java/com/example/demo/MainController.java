/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;
import lombok.extern.java.Log;
import org.hibernate.cfg.ManagedBeanSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author infoh
 */
@Controller
@Log
public class MainController {

    @Autowired
    UserService us;

    @Autowired
    UserRepository ur;

    @Autowired
    CartRepository cr;

    @Autowired
    BookRepository br;
    
    @Autowired
    CartService cs;
    
       public static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/uploads";

    @GetMapping("/upload") public String displayUploadForm() {
        return "upload";
    }

    @PostMapping("/upload")
    public String uploadImage(Model model, @RequestParam("image") MultipartFile file) throws IOException {
        Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, file.getOriginalFilename());
        Files.write(fileNameAndPath, file.getBytes());
        model.addAttribute("msg", "Uploaded images: " + file.getOriginalFilename());
        return "upload";
    }
    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/user")
    public String getUser(Authentication authentication, Model model) {
        model.addAttribute("username", authentication.getName());
        model.addAttribute("role", authentication.getAuthorities());
        return "user";
    }

    @GetMapping("/register")
    public String index(Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute("user", userDto);
        return "index";
    }

    @PostMapping("/register")
    public String postIndex(@ModelAttribute("user") @Valid UserDto user,
            BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("message", "Failed");
            return "index";
        }
        us.createUser(user);
        model.addAttribute("message", "Fine");
        return "redirect:/login";
    }

    @ExceptionHandler({RuntimeException.class})
    public String handleException(RuntimeException e, Model model) {
        model.addAttribute("error", e.getMessage());
        model.addAttribute("user", new UserDto());
        return "index";
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 *
 * @author infoh
 */
@Data
public class UserDto {
    @NotEmpty
    @Email(message = "Email convension")
    String email;
    
    @NotEmpty
    @Size(min = 4, message = "Password min 4")
    String password;
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author infoh
 */
@Component
@AllArgsConstructor
public class UniqueEmailConstraint implements ConstraintValidator<UniqueEmail, String>{
    
    @Autowired
    private UserRepository ur;    
    
    @Override
    public boolean isValid(String t, ConstraintValidatorContext cvc) {
        return ur.findByEmail(t).isEmpty();
    }
    
}

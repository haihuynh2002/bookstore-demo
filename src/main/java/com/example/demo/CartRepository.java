/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author infoh
 */
public interface CartRepository extends JpaRepository<Cart, Long>{
    @Query("SELECT c FROM Cart c JOIN c.user u WHERE u.email = :email")
    Optional<Cart> findByEmail(String email);
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author infoh
 */
@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartBookKey implements Serializable {
    
    @Column(name = "cart_id")
    private Long cartId;
    
    @Column(name = "book_id")
    private Long bookId;
    
        @Override
    public int hashCode() {
        return Objects.hash(bookId, cartId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartBookKey that = (CartBookKey) o;
        return Objects.equals(bookId, that.bookId) && Objects.equals(cartId, that.cartId);
    }
}
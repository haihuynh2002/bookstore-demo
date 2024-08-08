
package com.example.demo;

import com.example.demo.Book;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

/**
 *
 * @author infoh
 */
@Entity
@Table(name = "cart_book")
@Setter
@Getter
public class CartBook {

    @EmbeddedId
    @JsonIgnore
    private CartBookKey id = new CartBookKey();

    @ManyToOne
    @MapsId("bookId")
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne
    @MapsId("cartId")
    @JoinColumn(name = "cart_id")
    private Cart cart;
    
    Integer quantity;
    
    public void addCartBook(Cart cart, Book book) {
        this.setCart(cart);
        this.setBook(book);
        
        book.getCartBooks().add(this);
        cart.getCartBooks().add(this);
    }
}
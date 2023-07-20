package com.example.bookAppbe.controller;

import com.example.bookAppbe.domain.Book;
import com.example.bookAppbe.dto.BookDTO;
import com.example.bookAppbe.service.BookService;
import com.example.bookAppbe.service.UserService;
import jakarta.validation.Valid;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;


@RestController
public class TestController {
    private final BookService bookService;

    private final UserService userService;

    private BCryptPasswordEncoder passwordEncoder;

    public TestController(BookService bookService, UserService userService) {
        this.bookService = bookService;
        this.userService = userService;
    }

    @PostMapping("/")
    public String loginRequest(){
        return "return";
    }
//
//    @PostMapping("/logout")
//    public void performLogout(){
//         System.out.println("logout");
//    }

    @GetMapping("/users")
    public List fetchUsersList(){
        return Collections.emptyList();
    }


    @GetMapping("/books")
    public List fetchBookList(){
        return bookService.fetchBookList();
    }

    @PostMapping("/book")
    public Book saveBook( @Valid @RequestBody BookDTO book){
        return bookService.saveBook(book);
    }

    @GetMapping("/title/{title}")
    public BookDTO findBookByTitle(@PathVariable String title){

        return bookService.findBookByTitle(title);
    }

    @GetMapping("/author/{author}")
    public BookDTO findBookByAuthor(@PathVariable String author){

        return bookService.findBookByAuthor(author);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable("id") Long id){
        bookService.deleteBook(id);
    }


    @PutMapping("/{id}")
    public BookDTO updateBook(@PathVariable("id") Long id,@Valid @RequestBody BookDTO bookToUpdate ){
        return bookService.updateBook(id, bookToUpdate);

    }

    @PutMapping("/book/{bookId}/{borrow}/{userId}")
    public int updateBorrow(@PathVariable Boolean borrow, @PathVariable Long bookId, @PathVariable Long userId){
        return bookService.updateBorrow(borrow, bookId, userId);
    }


}

package com.example.bookAppbe.dto;

import com.example.bookAppbe.domain.Book;
import com.example.bookAppbe.domain.User;

public class BookDTO {

    Long id;
    String author;
    String title;
    User user;
    Boolean borrow;

    public BookDTO(Long id, String author, String title, User user, Boolean borrow) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.user = user;
        this.borrow = borrow;
    }

    public BookDTO(){

    }



    public Long getId() {
        return id;
    }

    public void setId(Long bookId) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {

        this.title = title;
    }

    public Boolean getBorrow() {
        return borrow;
    }

    public void setBorrow(Boolean borrow) {
        this.borrow = borrow;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public static Book mapDtoToEntityBook(BookDTO bookDTO) {
        Book newBook = new Book();
        newBook.setTitle(bookDTO.getTitle());
        newBook.setAuthor(bookDTO.getAuthor());
        newBook.setBorrow(bookDTO.getBorrow());
        newBook.setUser(bookDTO.getUser());
        return newBook;
    }


    public static BookDTO bookToEntityBookDto(Book book) {
        BookDTO newBook = new BookDTO();
        newBook.setId(book.getId());
        newBook.setTitle(book.getTitle());
        newBook.setAuthor(book.getAuthor());
        newBook.setBorrow(book.getBorrow());
        newBook.setUser(book.getUser());
        return newBook;
    }


}

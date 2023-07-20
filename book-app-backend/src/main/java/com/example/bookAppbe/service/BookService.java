package com.example.bookAppbe.service;

import com.example.bookAppbe.domain.Book;
import com.example.bookAppbe.dto.BookDTO;
import com.example.bookAppbe.exceptions.BookNotFoundExc;
import com.example.bookAppbe.repositories.BookRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class BookService {

    @Autowired
    private BookRepositories bookRepositories;

    public List fetchBookList(){
        return bookRepositories.findAll();
    }

    public Book saveBook(BookDTO book){
        return bookRepositories.save(BookDTO.mapDtoToEntityBook(book));
    }

    public BookDTO findBookByTitle(String title){
        return BookDTO.bookToEntityBookDto(bookRepositories.getFirstByTitle(title));
    }

    public BookDTO findBookByAuthor(String author){
        return BookDTO.bookToEntityBookDto(bookRepositories.getFirstByAuthor(author));
    }

    public void deleteBook(Long bookId){
        bookRepositories.deleteById(bookId);
    }

    public BookDTO updateBook(Long id, BookDTO updateBook) {
        Optional<Book> book = bookRepositories.findById(id);
        if (book.isPresent()) {
            Book bookFomDB = book.get();
            bookFomDB.setAuthor(updateBook.getAuthor());
            bookFomDB.setTitle(updateBook.getTitle());
            bookFomDB.setBorrow(updateBook.getBorrow());
            bookFomDB.setUser(updateBook.getUser());


            Book savedBook = bookRepositories.save(bookFomDB);
            return BookDTO.bookToEntityBookDto(savedBook);
        }
        throw new BookNotFoundExc();
    }

    public BookRepositories getBookRepositories() {
        return bookRepositories;
    }

    public void setBookRepositories(BookRepositories bookRepositories) {
        this.bookRepositories = bookRepositories;
    }

    public int updateBorrow(Boolean borrow, Long bookId, Long userId){
        System.out.println(" borrow "+ borrow);
        System.out.println(" bookId "+ bookId);
        System.out.println(" userId "+ userId);
        return bookRepositories.updateBorrow(borrow, bookId, userId);
    }

}

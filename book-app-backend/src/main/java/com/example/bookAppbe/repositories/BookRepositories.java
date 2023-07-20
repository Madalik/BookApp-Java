package com.example.bookAppbe.repositories;

import com.example.bookAppbe.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;



@Repository
public interface BookRepositories extends JpaRepository <Book, Long>{

    public Book getFirstByTitle(String title);

    public Book getFirstByAuthor(String author);

    @Modifying
    @Query("update Book b set b.borrow = :borrow, b.user.id = :userId where b.id = :bookId " )
    public int updateBorrow(Boolean borrow, Long bookId, Long userId);
}

package com.example.bookAppbe.controller;

import com.example.bookAppbe.domain.Book;
import com.example.bookAppbe.dto.BookDTO;
import com.example.bookAppbe.repositories.BookRepositories;
import com.example.bookAppbe.service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TestController.class)
public class TestControllerTest {

    @MockBean
    private BookRepositories bookRepositories;

    @MockBean
    private BookService bookService;

    @Autowired
    private MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void addBook() throws Exception{
        Book book = new Book();
        book.setId(13L);
        book.setAuthor("Paul");
        book.setTitle("Iarna");
        book.setBorrow(true);

        BookDTO bookDTO = new BookDTO();
        bookDTO.setAuthor("Jerry");
        bookDTO.setTitle("Summer");
        bookDTO.setBorrow(false);

        when(bookService.saveBook(any())).thenReturn(book);

        mockMvc.perform(post("/book").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(bookDTO))).andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(book.getId()))
                .andExpect(jsonPath("$.title").value(book.getTitle()))
                .andExpect(jsonPath("$.author").value(book.getAuthor()))
                .andExpect(jsonPath("$.borrow").value(book.getBorrow()))
                .andDo(print());
    }

    @Test
    void fetchBookList() throws Exception{
        mockMvc.perform(get("/")).andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void deleteBook() throws Exception{

        mockMvc.perform(delete("/13")).andExpect(status().isOk());
    }

    @Test
    void findBookByTitle() throws Exception{
        BookDTO book = new BookDTO();
        book.setId(13L);
        book.setAuthor("Paul");
        book.setTitle("Iarna");
        book.setBorrow(true);

        when(bookService.findBookByTitle(any())).thenReturn(book);

        mockMvc.perform(get("/title/Iarna"))
                .andExpect(status().isOk()).andDo(print());
    }

    @Test
    void findBookByAuthor() throws Exception{
        BookDTO book = new BookDTO();
        book.setId(13L);
        book.setAuthor("Paul");
        book.setTitle("Iarna");
        book.setBorrow(true);

        when(bookService.findBookByAuthor(any())).thenReturn(book);

        mockMvc.perform(get("/author/Paul"))
                .andExpect(status().isOk()).andDo(print());
    }

    @Test
    void updateBook() throws Exception{

        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(13L);
        bookDTO.setAuthor("Jerry");
        bookDTO.setTitle("Summer");
        bookDTO.setBorrow(false);

        when(bookService.updateBook(anyLong(),any())).thenReturn(bookDTO);

        mockMvc.perform(put("/13").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(bookDTO))).andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(bookDTO.getId()))
                .andExpect(jsonPath("$.title").value(bookDTO.getTitle()))
                .andExpect(jsonPath("$.author").value(bookDTO.getAuthor()))
                .andExpect(jsonPath("$.borrow").value(bookDTO.getBorrow()))
                .andDo(print());

    }

    @Test
    void updateBorrow() throws Exception{

        when(bookService.updateBorrow(anyBoolean(), anyLong(),anyLong())).thenReturn(1);

        mockMvc.perform(put("/book/5/true/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(1))
                .andDo(print());
    }



}

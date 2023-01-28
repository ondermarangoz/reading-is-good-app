package com.onder.readingisgood.controller.book;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.onder.readingisgood.service.book.BookService;
import com.onder.readingisgood.service.book.model.BookDTO;
import com.onder.readingisgood.service.book.request.RequestAddNewBook;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BookControllerTest {
    private final Long id = 1234L;
    private final String title = "title";
    private final String author = "author";
    private final float price = 50;
    private final String category = "Drama";
    private final int totalCount = 1;
    private final int addByNum = 1;
    private final int sold = 1;
    @MockBean
    private BookService bookService;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setup() {
        objectMapper = new ObjectMapper();
    }


    @Test
    void addNewBook() throws Exception {
        //Arrange
        BookDTO bookDto = BookDTO.builder()
                .id(id).title(title).author(author)
                .category(category).price(price)
                .totalCount(totalCount).build();
        RequestAddNewBook requestAddNewBook = RequestAddNewBook.builder()
                .bookDTO(bookDto).build();
        doNothing().when(bookService).addNewBook(requestAddNewBook);

        //Act and Assert
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/book/addNewBook")
                        .content(objectMapper.writeValueAsBytes(requestAddNewBook))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void addBook() throws Exception {

        String url = "/book/addBook/" + id + "/" + addByNum;
        doNothing().when(bookService).addBook(id, addByNum);
        mockMvc.perform(MockMvcRequestBuilders
                        .put(url))
                .andExpect(status().isOk());
    }
}
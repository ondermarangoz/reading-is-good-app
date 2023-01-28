package com.onder.readingisgood.controller.book;

import com.onder.readingisgood.domain.mapper.BookMapper;
import com.onder.readingisgood.domain.model.request.AddNewBookRequestModel;
import com.onder.readingisgood.service.book.BookService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/book")
public class BookController {
    private final BookService bookService;
    private final BookMapper mapper = Mappers.getMapper(BookMapper.class);

    @PostMapping("/addNewBook")
    @ResponseStatus(HttpStatus.CREATED)
    public void addNewBook(@Valid @RequestBody AddNewBookRequestModel requestModel) {
        bookService.addNewBook(mapper.newBookRequestModelToBookRequest(requestModel));
    }

    @PutMapping("/addBook/{id}/{quantityToAdd}")
    @ResponseStatus(HttpStatus.OK)
    public void addBook(@PathVariable Long id,
                        @PathVariable int quantityToAdd) {

        bookService.addBook(id, quantityToAdd);
    }

}

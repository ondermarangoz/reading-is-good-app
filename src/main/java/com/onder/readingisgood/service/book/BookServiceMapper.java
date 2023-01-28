package com.onder.readingisgood.service.book;

import com.onder.readingisgood.domain.entity.Book;
import com.onder.readingisgood.service.book.model.BookDTO;
import com.onder.readingisgood.service.book.request.RequestAddNewBook;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface BookServiceMapper {
    @Mapping(target = "id", source = "bookDTO.id")
    @Mapping(target = "title", source = "bookDTO.title")
    @Mapping(target = "author", source = "bookDTO.author")
    @Mapping(target = "category", source = "bookDTO.category")
    @Mapping(target = "price", source = "bookDTO.price")
    @Mapping(target = "totalCount", source = "bookDTO.totalCount")
    BookDTO mapRequestAddNewBookToBookDTO(RequestAddNewBook request);

    @Mapping(target = "bookId", source = "bookDTO.id")
    Book mapBookDtoToBook(BookDTO bookDTO);
}

package com.onder.readingisgood.service.book;

import com.onder.readingisgood.domain.entity.Book;
import com.onder.readingisgood.domain.repository.BookRepository;
import com.onder.readingisgood.infrastucture.exception.BookNotFoundException;
import com.onder.readingisgood.infrastucture.exception.DuplicateResourceException;
import com.onder.readingisgood.service.book.model.BookDTO;
import com.onder.readingisgood.service.book.request.RequestAddNewBook;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookServiceMapper mapper = Mappers.getMapper(BookServiceMapper.class);

    @Override
    @Transactional
    public void addNewBook(RequestAddNewBook request) {
        BookDTO bookDTO = mapper.mapRequestAddNewBookToBookDTO(request);
        Optional<Book> bookById = bookRepository.findById(bookDTO.getId());
        bookById.ifPresent(book -> {
            throw new DuplicateResourceException("Book with same id present. " +
                    "Either use update methods to update the book counts or use addBook(Long id, int quantityToAdd) methods");
        });
        log.info("No Duplicates found.");
        Book book = mapper.mapBookDtoToBook(bookDTO);
        log.info("The data are mapped and ready to save.");

        //Save to book
        bookRepository.save(book);
    }

    @Override
    public void addBook(Long id, int quantityToAdd) {
        //Get the book by id
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book with id:" + id + " is not registered. Use addNewBook to register."));
        log.info("The book with id " + id + " is registered");

        int totalCountAfterAdd = book.getTotalCount() + quantityToAdd;
        book.setTotalCount(totalCountAfterAdd);

        bookRepository.save(book);

    }
}

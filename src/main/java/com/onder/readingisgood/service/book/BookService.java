package com.onder.readingisgood.service.book;

import com.onder.readingisgood.service.book.request.RequestAddNewBook;

public interface BookService {

    void addNewBook(RequestAddNewBook request);

    void addBook(Long id, int quantityToAdd);

}

package com.onder.readingisgood.service.book.request;

import com.onder.readingisgood.service.book.model.BookDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RequestAddNewBook {
    private BookDTO bookDTO;
}

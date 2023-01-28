package com.onder.readingisgood.domain.model.request;

import com.onder.readingisgood.domain.model.dto.BookModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddNewBookRequestModel {
    private BookModel bookModel;
}

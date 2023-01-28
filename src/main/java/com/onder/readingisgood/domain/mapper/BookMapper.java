package com.onder.readingisgood.domain.mapper;

import com.onder.readingisgood.domain.model.request.AddNewBookRequestModel;
import com.onder.readingisgood.service.book.request.RequestAddNewBook;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface BookMapper {

    @Mapping(source = "bookModel", target = "bookDTO")
    RequestAddNewBook newBookRequestModelToBookRequest(AddNewBookRequestModel requestModel);
}

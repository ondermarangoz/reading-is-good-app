package com.onder.readingisgood.service.book.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class BookDTO {
    private Long id;
    private String title;
    private String author;
    private String category;
    private float price;
    private int totalCount;
}

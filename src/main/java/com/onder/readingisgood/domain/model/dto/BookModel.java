package com.onder.readingisgood.domain.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;

@Getter
@Setter
@Builder
public class BookModel {
    private Long id;
    private String title;
    private String author;
    private String category;
    private float price;
    private int totalCount;

}

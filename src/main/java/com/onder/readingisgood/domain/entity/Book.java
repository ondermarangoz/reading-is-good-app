package com.onder.readingisgood.domain.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity(name = "Books")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    @Id
    private Long bookId;

    @NotNull(message = "Title must not be empty.")
    private String title;

    private String author;

    private String category;

    @Min(value = 0, message = "Price must be positive value.")
    private float price;

    @Min(value = 0, message = "Total count must be positive value.")
    private int totalCount;

    @Min(value = 0, message = "Total sell should be positive value.")
    private int sold;

    @OneToMany(mappedBy = "book", cascade = CascadeType.PERSIST)
    private List<Order> order;



}
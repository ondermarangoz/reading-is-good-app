package com.onder.readingisgood.service.book;

import com.onder.readingisgood.domain.entity.Book;
import com.onder.readingisgood.domain.entity.Order;
import com.onder.readingisgood.domain.entity.User;
import com.onder.readingisgood.domain.repository.BookRepository;
import com.onder.readingisgood.infrastucture.exception.BookNotFoundException;
import com.onder.readingisgood.service.book.request.RequestAddNewBook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class BookServiceImplTest {

    @Mock
    private BookRepository mockBookRepository;

    private BookServiceImpl bookServiceImplUnderTest;

    @BeforeEach
    void setUp() {
        bookServiceImplUnderTest = new BookServiceImpl(mockBookRepository);
    }

    @Test
    void testAddNewBook() {
        final RequestAddNewBook request = RequestAddNewBook.builder().build();

        final Order order = new Order();
        order.setOrderId(0L);
        order.setOrderDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        order.setOrderCount(0);
        order.setPrice(0.0f);
        order.setBook(Book.builder()
                .totalCount(0)
                .build());
        order.setUser(User.builder().build());
        final Optional<Book> book = Optional.of(
                new Book(0L, "title", "author", "category", 0.0f, 0, 0, List.of(order)));
        when(mockBookRepository.findById(0L)).thenReturn(book);

        final Order order1 = new Order();
        order1.setOrderId(0L);
        order1.setOrderDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        order1.setOrderCount(0);
        order1.setPrice(0.0f);
        order1.setBook(Book.builder()
                .totalCount(0)
                .build());
        order1.setUser(User.builder().build());
        final Book book1 = new Book(0L, "title", "author", "category", 0.0f, 0, 0, List.of(order1));
        when(mockBookRepository.save(any(Book.class))).thenReturn(book1);

        bookServiceImplUnderTest.addNewBook(request);

        verify(mockBookRepository).save(any(Book.class));
    }

    @Test
    void testAddNewBookRepositoryFindByIdReturnsAbsent() {
        // Setup
        final RequestAddNewBook request = RequestAddNewBook.builder().build();
        when(mockBookRepository.findById(0L)).thenReturn(Optional.empty());

        final Order order = new Order();
        order.setOrderId(0L);
        order.setOrderDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        order.setOrderCount(0);
        order.setPrice(0.0f);
        order.setBook(Book.builder()
                .totalCount(0)
                .build());
        order.setUser(User.builder().build());
        final Book book = new Book(0L, "title", "author", "category", 0.0f, 0, 0, List.of(order));
        when(mockBookRepository.save(any())).thenReturn(book);

        bookServiceImplUnderTest.addNewBook(request);

        verify(mockBookRepository).save(any(Book.class));
    }

    @Test
    void testAddBook() {
        final Order order = new Order();
        order.setOrderId(0L);
        order.setOrderDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        order.setOrderCount(0);
        order.setPrice(0.0f);
        order.setBook(Book.builder()
                .totalCount(0)
                .build());
        order.setUser(User.builder().build());
        final Optional<Book> book = Optional.of(
                new Book(0L, "title", "author", "category", 0.0f, 0, 0, List.of(order)));
        when(mockBookRepository.findById(0L)).thenReturn(book);

        final Order order1 = new Order();
        order1.setOrderId(0L);
        order1.setOrderDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        order1.setOrderCount(0);
        order1.setPrice(0.0f);
        order1.setBook(Book.builder()
                .totalCount(0)
                .build());
        order1.setUser(User.builder().build());
        final Book book1 = new Book(0L, "title", "author", "category", 0.0f, 0, 0, List.of(order1));
        when(mockBookRepository.save(any(Book.class))).thenReturn(book1);

        bookServiceImplUnderTest.addBook(0L, 0);

        verify(mockBookRepository).save(any(Book.class));
    }

    @Test
    void testAddBookRepositoryFindByIdReturnsAbsent() {
        when(mockBookRepository.findById(0L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> bookServiceImplUnderTest.addBook(0L, 0)).isInstanceOf(BookNotFoundException.class);
    }
}

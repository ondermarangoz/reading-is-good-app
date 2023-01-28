package com.onder.readingisgood.service.customer;

import com.onder.readingisgood.domain.entity.Book;
import com.onder.readingisgood.domain.entity.Order;
import com.onder.readingisgood.domain.entity.User;
import com.onder.readingisgood.domain.entity.UserRole;
import com.onder.readingisgood.domain.repository.OrderRepository;
import com.onder.readingisgood.domain.repository.UserRepository;
import com.onder.readingisgood.service.customer.request.RequestGetCustomer;
import com.onder.readingisgood.service.customer.request.RequestGetCustomerOrder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class CustomerServiceImplTest {

    @Mock
    private UserRepository mockUserRepository;
    @Mock
    private OrderRepository mockOrderRepository;
    @Mock
    private BCryptPasswordEncoder mockBCryptPasswordEncoder;

    private CustomerServiceImpl customerServiceImplUnderTest;

    @BeforeEach
    void setUp() {
        customerServiceImplUnderTest = new CustomerServiceImpl(mockUserRepository, mockOrderRepository,
                mockBCryptPasswordEncoder);
    }

    @Test
    public void testSignUpUser() {
        final Order order = new Order();
        order.setOrderId(0L);
        order.setOrderDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        order.setOrderCount(0);
        order.setPrice(0.0f);
        order.setBook(Book.builder().build());
        order.setUser(User.builder()
                .email("name@gmail.com")
                .password("password")
                .build());
        final User user = new User(0L, "name", "surname", "name@gmail.com", "password", UserRole.ADMIN, false, false,
                List.of(order));
        final Order order1 = new Order();
        order1.setOrderId(0L);
        order1.setOrderDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        order1.setOrderCount(0);
        order1.setPrice(0.0f);
        order1.setBook(Book.builder().build());
        order1.setUser(User.builder()
                .email("name@gmail.com")
                .password("password")
                .build());
        final User expectedResult = new User(0L, "name", "surname", "name@gmail.com", "password", UserRole.ADMIN, false, false,
                List.of(order1));
        when(mockUserRepository.existsByEmail("name@gmail.com")).thenReturn(false);
        when(mockBCryptPasswordEncoder.encode("password")).thenReturn("password");

        final Order order2 = new Order();
        order2.setOrderId(0L);
        order2.setOrderDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        order2.setOrderCount(0);
        order2.setPrice(0.0f);
        order2.setBook(Book.builder().build());
        order2.setUser(User.builder()
                .email("name@gmail.com")
                .password("password")
                .build());
        final User user1 = new User(0L, "name", "surname", "name@gmail.com", "password", UserRole.ADMIN, false, false,
                List.of(order2));
        when(mockUserRepository.save(new User(0L, "name", "surname", "name@gmail.com", "password", UserRole.ADMIN, false, false,
                List.of(new Order())))).thenReturn(user1);

        final User result = customerServiceImplUnderTest.signUpUser(user);

    }

    @Test
    public void testGetCustomer() {
        final RequestGetCustomer requestGetCustomer = RequestGetCustomer.builder()
                .email("userEmail")
                .build();
        final Order order = new Order();
        order.setOrderId(0L);
        order.setOrderDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        order.setOrderCount(0);
        order.setPrice(0.0f);
        order.setBook(Book.builder().build());
        order.setUser(User.builder()
                .email("email")
                .password("password")
                .build());
        final User expectedResult = new User(0L, "name", "surname", "email", "password", UserRole.ADMIN, false, false,
                List.of(order));

        final Order order1 = new Order();
        order1.setOrderId(0L);
        order1.setOrderDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        order1.setOrderCount(0);
        order1.setPrice(0.0f);
        order1.setBook(Book.builder().build());
        order1.setUser(User.builder()
                .email("email")
                .password("password")
                .build());
        final Optional<User> user = Optional.of(
                new User(0L, "name", "surname", "email", "password", UserRole.ADMIN, false, false, List.of(order1)));
        when(mockUserRepository.findUserEntitiesByEmail("userEmail")).thenReturn(user);

        final User result = customerServiceImplUnderTest.getCustomer(requestGetCustomer);

        assertThat(result.getEmail()).isEqualTo(expectedResult.getEmail());
    }

    @Test
    public void testGetCustomerUserRepositoryReturnsAbsent() {
        final RequestGetCustomer requestGetCustomer = RequestGetCustomer.builder()
                .email("userEmail")
                .build();
        when(mockUserRepository.findUserEntitiesByEmail("userEmail")).thenReturn(Optional.empty());

        assertThatThrownBy(() -> customerServiceImplUnderTest.getCustomer(requestGetCustomer))
                .isInstanceOf(UsernameNotFoundException.class);
    }

    @Test
    public void testLoadUserByUsername() {
        final Order order = new Order();
        order.setOrderId(0L);
        order.setOrderDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        order.setOrderCount(0);
        order.setPrice(0.0f);
        order.setBook(Book.builder().build());
        order.setUser(User.builder()
                .email("userEmail")
                .password("password")
                .build());
        final User expectedResult = new User(0L, "name", "surname", "userEmail", "password", UserRole.ADMIN, false, false,
                List.of(order));

        final Order order1 = new Order();
        order1.setOrderId(0L);
        order1.setOrderDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        order1.setOrderCount(0);
        order1.setPrice(0.0f);
        order1.setBook(Book.builder().build());
        order1.setUser(User.builder()
                .email("userEmail")
                .password("password")
                .build());
        final Optional<User> user = Optional.of(
                new User(0L, "name", "surname", "userEmail", "password", UserRole.ADMIN, false, false, List.of(order1)));
        when(mockUserRepository.findUserEntitiesByEmail("userEmail")).thenReturn(user);

        final User result = customerServiceImplUnderTest.loadUserByUsername("userEmail");

        assertThat(result.getEmail()).isEqualTo(expectedResult.getEmail());
    }

    @Test
    public void testLoadUserByUsernameUserRepositoryReturnsAbsent() {
        when(mockUserRepository.findUserEntitiesByEmail("userEmail")).thenReturn(Optional.empty());

        assertThatThrownBy(() -> customerServiceImplUnderTest.loadUserByUsername("userEmail"))
                .isInstanceOf(UsernameNotFoundException.class);
    }

    @Test
    public void testLoadUserByUsernameThrowsException() {
        final Order order = new Order();
        order.setOrderId(0L);
        order.setOrderDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        order.setOrderCount(0);
        order.setPrice(0.0f);
        order.setBook(Book.builder().build());
        order.setUser(User.builder()
                .email("email")
                .password("password")
                .build());
        final Optional<User> user = Optional.of(
                new User(0L, "name", "surname", "email", "password", UserRole.ADMIN, false, false, List.of(order)));
        when(mockUserRepository.findUserEntitiesByEmail("email")).thenReturn(user);

        assertThatThrownBy(() -> customerServiceImplUnderTest.loadUserByUsername("userEmail"))
                .isInstanceOf(Exception.class);
    }

    @Test
    public void testGetOrderOfCustomer() {
        final RequestGetCustomerOrder requestGetCustomerOrder = new RequestGetCustomerOrder();
        requestGetCustomerOrder.setEmail("email");

        final Order order = new Order();
        order.setOrderId(0L);
        order.setOrderDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        order.setOrderCount(0);
        order.setPrice(0.0f);
        order.setBook(Book.builder().build());
        order.setUser(User.builder()
                .email("email")
                .password("password")
                .build());
        final Optional<User> user = Optional.of(
                new User(0L, "name", "surname", "email", "password", UserRole.ADMIN, false, false, List.of(order)));
        when(mockUserRepository.findUserEntitiesByEmail("email")).thenReturn(user);

        final Order order1 = new Order();
        order1.setOrderId(0L);
        order1.setOrderDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        order1.setOrderCount(0);
        order1.setPrice(0.0f);
        order1.setBook(Book.builder().build());
        order1.setUser(User.builder()
                .email("email")
                .password("password")
                .build());
        final List<Order> orders = List.of(order1);
        when(mockOrderRepository.getOrdersByUser(Optional.of(
                new User(0L, "name", "surname", "email", "password", UserRole.ADMIN, false, false,
                        List.of(new Order()))))).thenReturn(orders);

        final List<Order> result = customerServiceImplUnderTest.getOrderOfCustomer(requestGetCustomerOrder);


    }

    @Test
    public void testGetOrderOfCustomerUserRepositoryReturnsAbsent() {
        final RequestGetCustomerOrder requestGetCustomerOrder = new RequestGetCustomerOrder();
        requestGetCustomerOrder.setEmail("email");

        when(mockUserRepository.findUserEntitiesByEmail("email")).thenReturn(Optional.empty());

        final Order order = new Order();
        order.setOrderId(0L);
        order.setOrderDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        order.setOrderCount(0);
        order.setPrice(0.0f);
        order.setBook(Book.builder().build());
        order.setUser(User.builder()
                .email("email")
                .password("password")
                .build());
        final List<Order> orders = List.of(order);
        when(mockOrderRepository.getOrdersByUser(Optional.of(
                new User(0L, "name", "surname", "email", "password", UserRole.ADMIN, false, false,
                        List.of(new Order()))))).thenReturn(orders);
        final List<Order> result = customerServiceImplUnderTest.getOrderOfCustomer(requestGetCustomerOrder);
        Assertions.assertNotNull(result);
    }

    @Test
    public void testGetOrderOfCustomerOrderRepositoryReturnsNoItems() {
        final RequestGetCustomerOrder requestGetCustomerOrder = new RequestGetCustomerOrder();
        requestGetCustomerOrder.setEmail("email");

        final Order order = new Order();
        order.setOrderId(0L);
        order.setOrderDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        order.setOrderCount(0);
        order.setPrice(0.0f);
        order.setBook(Book.builder().build());
        order.setUser(User.builder()
                .email("email")
                .password("password")
                .build());
        final Optional<User> user = Optional.of(
                new User(0L, "name", "surname", "email", "password", UserRole.ADMIN, false, false, List.of(order)));
        when(mockUserRepository.findUserEntitiesByEmail("email")).thenReturn(user);

        when(mockOrderRepository.getOrdersByUser(Optional.of(
                new User(0L, "name", "surname", "email", "password", UserRole.ADMIN, false, false,
                        List.of(new Order()))))).thenReturn(Collections.emptyList());

        final List<Order> result = customerServiceImplUnderTest.getOrderOfCustomer(requestGetCustomerOrder);

        assertThat(result).isEqualTo(Collections.emptyList());
    }
}

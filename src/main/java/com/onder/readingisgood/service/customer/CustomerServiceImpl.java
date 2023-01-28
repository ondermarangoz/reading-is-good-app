package com.onder.readingisgood.service.customer;

import com.onder.readingisgood.domain.entity.Order;
import com.onder.readingisgood.domain.entity.User;
import com.onder.readingisgood.domain.repository.OrderRepository;
import com.onder.readingisgood.domain.repository.UserRepository;
import com.onder.readingisgood.infrastucture.exception.EmailExistsException;
import com.onder.readingisgood.infrastucture.exception.EmailNotValidException;
import com.onder.readingisgood.service.customer.request.RequestGetCustomer;
import com.onder.readingisgood.service.customer.request.RequestGetCustomerOrder;
import lombok.AllArgsConstructor;
import org.apache.commons.validator.routines.EmailValidator;
import org.mapstruct.factory.Mappers;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService, UserDetailsService {

    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final CustomerServiceMapper mapper = Mappers.getMapper(CustomerServiceMapper.class);

    @Override
    public User signUpUser(User user) {
        boolean emailExist = userRepository.existsByEmail(user.getEmail());
        boolean isValidEmail = EmailValidator.getInstance().isValid(user.getEmail());
        if (emailExist) {
            throw new EmailExistsException("This email has already used");
        }
        if (!isValidEmail) {
            throw new EmailNotValidException("This email is not valid");
        }

        final String encryptedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);
        userRepository.save(user);

        return user;
    }

    @Override
    public User getCustomer(RequestGetCustomer requestGetCustomer) {

        return loadUserByUsername(requestGetCustomer.getEmail());

    }

    @Override
    public User loadUserByUsername(String userEmail) throws UsernameNotFoundException {

        final Optional<User> optionalUser = userRepository.findUserEntitiesByEmail(userEmail);

        if (optionalUser.isPresent()) {
            return optionalUser.get();

        } else {
            throw new UsernameNotFoundException(MessageFormat.format("User with email {0} cannot be found.", userEmail));
        }

    }

    @Override
    public List<Order> getOrderOfCustomer(RequestGetCustomerOrder requestGetCustomerOrder) {
        String email = mapper.mapRequestCustomerOrderToEmail(requestGetCustomerOrder);
        Optional<User> user = userRepository.findUserEntitiesByEmail(email);
        List<Order> orders = orderRepository.getOrdersByUser(user);
        return orders;

    }


}

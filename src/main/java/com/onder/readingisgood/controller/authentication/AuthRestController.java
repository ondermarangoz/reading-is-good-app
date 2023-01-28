package com.onder.readingisgood.controller.authentication;

import com.onder.readingisgood.domain.entity.User;
import com.onder.readingisgood.domain.mapper.CustomerMapper;
import com.onder.readingisgood.domain.model.request.AuthRequest;
import com.onder.readingisgood.domain.model.dto.UserModel;
import com.onder.readingisgood.infrastucture.security.JwtUtil;
import com.onder.readingisgood.service.customer.CustomerServiceImpl;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthRestController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    private final CustomerMapper mapper = Mappers.getMapper(CustomerMapper.class);

    @Autowired
    private CustomerServiceImpl customerService;

    @PostMapping("/login")
    public String creteToken(@RequestBody AuthRequest authRequest) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        } catch (BadCredentialsException ex) {
            throw new Exception("Incorrect username or password", ex);
        }
        final UserDetails userDetails = customerService.loadUserByUsername(authRequest.getUsername());
        return jwtUtil.generateToken(userDetails);

    }

    @PostMapping("/signUp")
    public ResponseEntity<String> signUp(@RequestBody UserModel user) {
        User createdUser = customerService.signUpUser(mapper.mapToUser(user));
        return new ResponseEntity<>("Created: " + mapper.mapToUserDTO(createdUser).getEmail(), HttpStatus.CREATED);
    }

}

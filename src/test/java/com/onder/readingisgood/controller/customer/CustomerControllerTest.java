package com.onder.readingisgood.controller.customer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.onder.readingisgood.domain.entity.User;
import com.onder.readingisgood.domain.entity.UserRole;
import com.onder.readingisgood.domain.model.dto.UserModel;
import com.onder.readingisgood.service.customer.CustomerServiceImpl;
import com.onder.readingisgood.service.customer.request.RequestGetCustomer;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class CustomerControllerTest {
    @MockBean
    private CustomerServiceImpl customerService;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;

    private final Long id = 1234L;

    @Before
    public void setup() {
        objectMapper = new ObjectMapper();
    }
    @Test
    void signUp() throws Exception {
        UserModel userModel = UserModel.builder()
                .email("test@gmail.com")
                .name("test")
                .surname("test")
                .password("test")
                .build();
        User user = User.builder()
                .email("test@gmail.com")
                .userRole(UserRole.USER)
                .name("test")
                .surname("test")
                .password("test")
                .build();
        when((customerService).signUpUser(user)).thenReturn(user);


        mockMvc.perform(MockMvcRequestBuilders
                        .post("/customer/signUp")
                        .content(objectMapper.writeValueAsBytes(userModel))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void getCustomer() throws Exception {


        RequestGetCustomer requestModel =
                RequestGetCustomer.builder()
                .email("test@gmail.com")
                .build();
        User user = User.builder()
                .email("test@gmail.com")
                .name("test")
                .surname("test")
                .password("test")
                .build();
        when(customerService.getCustomer(requestModel)).thenReturn(user);
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/customer/getCustomer")
                        .content(objectMapper.writeValueAsBytes(requestModel))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}
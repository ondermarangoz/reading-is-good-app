package com.onder.readingisgood.domain.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Validated
@Builder
public class UserModel {
    @NonNull
    private String name;
    @NonNull
    private String surname;
    @NonNull
    private String password;
    @NonNull
    private String email;
}

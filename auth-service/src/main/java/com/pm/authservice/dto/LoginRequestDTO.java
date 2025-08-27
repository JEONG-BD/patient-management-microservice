package com.pm.authservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;


public record LoginRequestDTO(
        @NotBlank(message = "Email is required")
        @Email(message = "Email should be a valid email address")
        String email,

        @NotBlank(message = "Password")
        @Size(min=8, message = "Password must be at leat 8 characters long")
        String password
        ) {
}

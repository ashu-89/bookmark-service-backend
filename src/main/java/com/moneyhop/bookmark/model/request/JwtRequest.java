package com.moneyhop.bookmark.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtRequest {

    @NotNull(message = "User name should not be null.")
    @NotEmpty(message = "User name should not be empty.")
    private String userName;

    @NotNull(message = "Password should not be null.")
    @NotEmpty(message = "Password should not be empty.")
    @Size(min = 8, max = 16, message = "Password should be between 8 and 16 characters long.")
    private String password;

}

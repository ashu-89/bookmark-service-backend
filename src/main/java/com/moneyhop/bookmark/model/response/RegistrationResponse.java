package com.moneyhop.bookmark.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationResponse {

    private Long id;
    private String userName;
    private String email;
    private String role;
}

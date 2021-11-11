package com.moneyhop.bookmark.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserNameExistsException extends Exception {

    private String code;
    private String message;
}

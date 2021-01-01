package com.example.restfulwebservice.User;
//HTTP Status code
//2xx -> Ok
//4xx -> Client error
//5xx -> Server error

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
//데이터가 존재하지 않는다는 오류 5xx (x) -> 4xx(o)
@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
}

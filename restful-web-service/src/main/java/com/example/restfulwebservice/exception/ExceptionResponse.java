package com.example.restfulwebservice.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
//AOP : 모든 컨트롤러에서 항상 실행시켜줘야하는 공통 항목
public class ExceptionResponse {
    private Date timestamp;
    private String message;
    private String details;
}

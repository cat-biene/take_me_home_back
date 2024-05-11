package ait.cohort34.accounting.dto.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class UserNotAgreeException extends RuntimeException{
    public UserNotAgreeException(String message) {
        super(message);
    }
}

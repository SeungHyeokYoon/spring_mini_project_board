package com.project2.board.exception.user;

import com.project2.board.exception.ClientErrorException;
import org.springframework.http.HttpStatus;

public class UserNotAllowedException extends ClientErrorException {

    public UserNotAllowedException() {
        super(HttpStatus.FORBIDDEN, "User not allowed.");
    }

}

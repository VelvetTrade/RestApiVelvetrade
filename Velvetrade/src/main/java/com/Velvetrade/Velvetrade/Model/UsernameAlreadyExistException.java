package com.Velvetrade.Velvetrade.Model;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "ID does not exist")
public class UsernameAlreadyExistException extends Exception {

}

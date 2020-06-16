package com.Velvetrade.Velvetrade.Model;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.PRECONDITION_FAILED, reason = "Username of group or user already exists")
public class InvalidNewUserException extends Exception {

}

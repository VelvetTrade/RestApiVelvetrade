package com.Velvetrade.Velvetrade.Model;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "ID does not exist")
public class IdNotFoundException extends Exception {

}

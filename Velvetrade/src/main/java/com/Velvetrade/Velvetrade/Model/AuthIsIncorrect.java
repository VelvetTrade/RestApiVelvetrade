package com.Velvetrade.Velvetrade.Model;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED, reason = "The password or username entered is incorrect")
public class AuthIsIncorrect extends Exception {

}

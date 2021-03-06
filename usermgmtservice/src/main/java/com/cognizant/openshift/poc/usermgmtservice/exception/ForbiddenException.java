package com.cognizant.openshift.poc.usermgmtservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN, reason="Access denied")
public class ForbiddenException extends RuntimeException {

}

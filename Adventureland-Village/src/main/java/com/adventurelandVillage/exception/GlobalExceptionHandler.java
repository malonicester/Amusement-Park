package com.adventurelandVillage.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(AdminException.class)
	public ResponseEntity<MyErrorDetails> adminException(AdminException adminException, WebRequest webRequest) {
		MyErrorDetails details = new MyErrorDetails();
		details.setTimestamp(LocalDateTime.now());
		details.setMessage(adminException.getMessage());
		details.setDetails(webRequest.getDescription(false));
		return new ResponseEntity<MyErrorDetails>(details, HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(CustomerException.class)
	public ResponseEntity<MyErrorDetails> customerException(CustomerException customerException,
			WebRequest webRequest) {
		MyErrorDetails details = new MyErrorDetails();
		details.setTimestamp(LocalDateTime.now());
		details.setMessage(customerException.getMessage());
		details.setDetails(webRequest.getDescription(false));
		return new ResponseEntity<MyErrorDetails>(details, HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(LoginException.class)
	public ResponseEntity<MyErrorDetails> loginException(LoginException loginException, WebRequest webRequest) {
		MyErrorDetails details = new MyErrorDetails();
		details.setTimestamp(LocalDateTime.now());
		details.setMessage(loginException.getMessage());
		details.setDetails(webRequest.getDescription(false));
		return new ResponseEntity<MyErrorDetails>(details, HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(ActivityException.class)
	public ResponseEntity<MyErrorDetails> activityException(ActivityException activityException,
			WebRequest webRequest) {
		MyErrorDetails details = new MyErrorDetails();
		details.setTimestamp(LocalDateTime.now());
		details.setMessage(activityException.getMessage());
		details.setDetails(webRequest.getDescription(false));
		return new ResponseEntity<MyErrorDetails>(details, HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(TicketException.class)
	public ResponseEntity<MyErrorDetails> ticketException(TicketException ticketException, WebRequest webRequest) {
		MyErrorDetails details = new MyErrorDetails();
		details.setTimestamp(LocalDateTime.now());
		details.setMessage(ticketException.getMessage());
		return new ResponseEntity<MyErrorDetails>(details, HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<MyErrorDetails> validationException(MethodArgumentNotValidException exception) {
		MyErrorDetails errorDetails = new MyErrorDetails();
		errorDetails.setTimestamp(LocalDateTime.now());
		errorDetails.setMessage("Validation Error");
		errorDetails.setDetails(exception.getBindingResult().getFieldError().getDefaultMessage());
		return new ResponseEntity<MyErrorDetails>(errorDetails, HttpStatus.BAD_REQUEST);
	}

	// to handle Not found exception
	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<MyErrorDetails> mynotFoundHandler(NoHandlerFoundException exception, WebRequest req) {
		MyErrorDetails err = new MyErrorDetails(LocalDateTime.now(), exception.getMessage(), req.getDescription(false));
		return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<MyErrorDetails> myExceptionHandler(Exception e, WebRequest req) {
		MyErrorDetails err = new MyErrorDetails(LocalDateTime.now(), e.getMessage(), req.getDescription(false));
		return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);

	}
}

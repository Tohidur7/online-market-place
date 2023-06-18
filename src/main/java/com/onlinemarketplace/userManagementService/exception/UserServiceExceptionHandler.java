//package com.onlinemarketplace.userManagementService.exception;
//
//import com.onlinemarketplace.userManagementService.responseData.UserErrorResponse;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.context.request.WebRequest;
//
//import java.util.Date;
//
//@ControllerAdvice
//public class UserServiceExceptionHandler {
//
//    //specific exception handler
//    @ExceptionHandler(UserNotFoundException.class)
//    public ResponseEntity<UserErrorResponse> handleUserNotFoundException(UserNotFoundException exception , WebRequest webRequest) {
//
//        UserErrorResponse userErrorResponse = new UserErrorResponse(new Date(), exception.getMessage(), webRequest.getDescription(false));
//        return new ResponseEntity<>(userErrorResponse, HttpStatus.NOT_FOUND);
//    }
//
//    @ExceptionHandler(UserAlreadyExistException.class)
//    public ResponseEntity<UserErrorResponse> handleUserAlreadyExistException(UserAlreadyExistException exception , WebRequest webRequest) {
//
//        UserErrorResponse userErrorResponse = new UserErrorResponse(new Date(), exception.getMessage(), webRequest.getDescription(false));
//        return new ResponseEntity<>(userErrorResponse, HttpStatus.NOT_FOUND);
//    }
//}

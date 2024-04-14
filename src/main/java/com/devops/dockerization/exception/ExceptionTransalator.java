package com.devops.dockerization.exception;

import com.devops.dockerization.customException.NoUserExistsException;
import com.devops.dockerization.customException.UserAlreadyExistsException;
import com.devops.dockerization.customException.UserNotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.List;

/**
 * @Controller Advice Applies To All Controllers
 */
@ControllerAdvice(basePackages = "com.devops.dockerization.controller")
public class ExceptionTransalator<T> {

    Logger log = LogManager.getLogger(ExceptionTransalator.class);

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<T> handleInternalServerError(Exception ex){
        ValidationFieldError validationFieldError = new ValidationFieldError(ex.getMessage(),ErrorCode.INTERNAL_SERVER_ERROR.getCode(),
                messageSource.getMessage("internal.server.error",null , LocaleContextHolder.getLocale()));
        return new ResponseEntity<>((T)validationFieldError, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<T> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        List<ValidationFieldError> validationFieldErrorList = new ArrayList<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->{
            validationFieldErrorList.add(new ValidationFieldError(error.getField(),error.getCode(),messageSource.getMessage(error.getDefaultMessage(),null,LocaleContextHolder.getLocale())));
        });
        ValidationErrorResponse validationErrorResponse = new ValidationErrorResponse(ErrorCode.BAD_REQUEST.getCode(),
                messageSource.getMessage("bad.request",null , LocaleContextHolder.getLocale()),validationFieldErrorList);
        return new ResponseEntity<>((T)validationErrorResponse,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<T> handleUserAlreadyExistsException(UserAlreadyExistsException ex){
        ValidationFieldError validationFieldError = new ValidationFieldError(ex.getFieldName(), ex.getValue(),
                messageSource.getMessage("user.already.exists",null, LocaleContextHolder.getLocale()));
        return new ResponseEntity<>((T)validationFieldError,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<T> handleUserNotFoundException(UserNotFoundException ex){
        ValidationFieldError validationFieldError = new ValidationFieldError(ex.getFieldName(), ErrorCode.NOT_FOUND.getCode(),
                messageSource.getMessage("user.not.found",null,LocaleContextHolder.getLocale()));
        return new ResponseEntity<>((T)validationFieldError,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoUserExistsException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<T> handleNoUsersFoundException(NoUserExistsException ex){

        ValidationFieldError error = new ValidationFieldError(ex.getFieldName(),ErrorCode.NOT_FOUND.getCode(),
                messageSource.getMessage("no.users.exists",null,LocaleContextHolder.getLocale()));
        return new ResponseEntity<>((T) error,HttpStatus.NOT_FOUND);
    }
}

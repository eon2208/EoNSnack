package com.eon.restaurant.eonsnack.server.exceptions;

import java.util.Date;
import java.util.UUID;

import lombok.extern.slf4j.Slf4j;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;


@Slf4j
@RestControllerAdvice
public class ApiResponseEntityExceptionHandler {

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
    public ErrorMessage resourceNotFoundHandler(ResourceNotFoundException ex, WebRequest request) {
        return new ErrorMessage(
                HttpStatus.NOT_FOUND.value(),
                new Date(),
                ex.getMessage(),
                request.getDescription(false));

    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidInputException.class)
    public ErrorMessage invalidInputHandler(InvalidInputException ex, WebRequest request) {
        return new ErrorMessage(
                HttpStatus.BAD_REQUEST.value(),
                new Date(),
                ex.getMessage(),
                request.getDescription(false));

    }

    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
    @ResponseStatus(code = HttpStatus.NOT_ACCEPTABLE)
    public ErrorMessage HttpMediaTypeNotSupportedHandler(HttpMediaTypeNotAcceptableException ex) {
        String acceptable = "Acceptable Media types: " + MediaType.toString(ex.getSupportedMediaTypes());

            return new ErrorMessage(
                    HttpStatus.NOT_ACCEPTABLE.value(),
                    new Date(),
                    ex.getMessage(),
                    acceptable);
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    @ResponseStatus(code = HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    public ErrorMessage HttpMediaTypeNotSupportedHandler(HttpMediaTypeNotSupportedException ex) {
        String unsupported = "Unsupported content type: " + ex.getContentType();
        String supported = "Supported content types: " + MediaType.toString(ex.getSupportedMediaTypes());

        return new ErrorMessage(
                HttpStatus.UNSUPPORTED_MEDIA_TYPE.value(),
                new Date(),
                ex.getMessage(),
                String.format("%s.%s", unsupported, supported));
    }


    @ExceptionHandler(Throwable.class)
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorMessage internalServerErrorHandler(final Throwable e) {

        String message = "API internal server problem.";

        UUID uuid = UUID.randomUUID();
        String errorRef = uuid.toString();
        log.error("errorRef=" + errorRef, message, e);

        return new ErrorMessage(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                new Date(),
                e.getMessage(),
                message);
    }

}

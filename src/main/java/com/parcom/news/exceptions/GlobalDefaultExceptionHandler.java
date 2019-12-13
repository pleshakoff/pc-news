package com.parcom.news.exceptions;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static java.util.Optional.ofNullable;


@ControllerAdvice
@RequiredArgsConstructor
public class GlobalDefaultExceptionHandler {

    private final MessageSource messageSource;
    private static final Logger logger = LoggerFactory.getLogger(GlobalDefaultExceptionHandler.class);

    private static ExceptionResource getExceptionResource(HttpServletRequest request, Exception ex, String message) {
        logger.error(String.format("Method: \"%s\"; URI: \"%s\" ", request.getMethod(), request.getRequestURI().toString()));
        logger.error(ex.getMessage(), ex);
        ExceptionResource result = new ExceptionResource();
        result.setUrl(request.getRequestURI().toString());
        result.setExceptionClass(ex.getClass().getName());
        result.setMethod(request.getMethod());
        result.setMessage(message);
        return result;
    }

    public static ExceptionResource getExceptionResource(HttpServletRequest request, Exception ex, String message, String description) {
        ExceptionResource result =  getExceptionResource(request,ex,message);
        result.setDescription(description);
        return result;
    }

    @ExceptionHandler(value = Exception.class)
    public ExceptionResource handleAllException(HttpServletRequest request, Exception ex, HttpServletResponse response) {
        ExceptionResource result = getExceptionResource(request, ex, getMessageForRootException(ex));
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        if (((ex.getClass() == AccessDeniedException.class) ||
                ((ex.getClass() == BadCredentialsException.class))) ||
                ((ex.getClass() == InternalAuthenticationServiceException.class))) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
        return result;
    }

    private String getMessageForRootException(Exception ex) {
        if (ex.getMessage() == null) {
            String mess = ofNullable(ex.getCause()).map(Object::toString).orElse(ex.getClass().toString());
            return messageSource.getMessage("exception.internal_server_error",
                    new String[]{mess}, "Internal server error", LocaleContextHolder.getLocale());
        } else
            return ex.getMessage();
    }




}
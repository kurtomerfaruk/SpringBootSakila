package com.kurtomerfaruk.springbootsakila.controllers;

import com.kurtomerfaruk.springbootsakila.attributes.DefaultExceptionAttributes;
import com.kurtomerfaruk.springbootsakila.attributes.ExceptionAttributes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author Omer Faruk KURT kurtomerfaruk@gmail.com
 * @version 1.0.0
 * @since 20.05.2023 10:39
 */
public class BaseController {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(NoResultException.class)
    public ResponseEntity<Map<String, Object>> handleNoResultException(
            NoResultException noResultException, HttpServletRequest request) {

        logger.info("> handleNoResultException");

        ExceptionAttributes exceptionAttributes = new DefaultExceptionAttributes();

        Map<String, Object> responseBody = exceptionAttributes
                .getExceptionAttributes(noResultException, request,
                        HttpStatus.NOT_FOUND);

        logger.info("< handleNoResultException");
        return new ResponseEntity<Map<String, Object>>(responseBody,
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleException(
            Exception exception, HttpServletRequest request) {

        logger.error("> handleException");
        logger.error("- Exception: ", exception);

        ExceptionAttributes exceptionAttributes = new DefaultExceptionAttributes();

        Map<String, Object> responseBody = exceptionAttributes
                .getExceptionAttributes(exception, request,
                        HttpStatus.INTERNAL_SERVER_ERROR);

        logger.error("< handleException");
        return new ResponseEntity<Map<String, Object>>(responseBody,
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

}

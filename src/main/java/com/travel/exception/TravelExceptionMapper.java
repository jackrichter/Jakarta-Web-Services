package com.travel.exception;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class TravelExceptionMapper implements ExceptionMapper<ConstraintViolationException> {

    @Override
    public Response toResponse(ConstraintViolationException exception) {
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(getCustomMessage(exception))
                .type(MediaType.TEXT_PLAIN)
                .build();
    }

    private String getCustomMessage(ConstraintViolationException exception) {
        String message = "Message from Exception Mapper: There was an error! ";
        for (ConstraintViolation<?> violation : exception.getConstraintViolations()) {
            message += violation.getMessage() + "\n";
        }
        return message;
    }
}

package org.falcon.shop.exceptions.handlers;

import org.falcon.shop.exceptions.InvalidParameterException;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Exception handler for {@link InvalidParameterException}.
 */
@Provider
public class InvalidParameterExceptionHandler implements ExceptionMapper<InvalidParameterException> {
    @Override
    public Response toResponse(InvalidParameterException exception) {
        return Response.status(Status.BAD_REQUEST).entity(exception.getMessage()).build();
    }
}

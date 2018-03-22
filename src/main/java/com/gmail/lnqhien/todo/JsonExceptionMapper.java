package com.gmail.lnqhien.todo;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import com.gmail.lnqhien.todo.entity.Error;

/**
 * Return JSON payload for exceptions, e.g.:
 *  {@code {"status":404,"message":"HTTP 404 Not Found"} }
 */
public class JsonExceptionMapper implements ExceptionMapper<Exception> {

	@Override
	public Response toResponse(Exception e) {
		// Everything is a 500, unless WebApplicationException which can indicate otherwise 
		int status = Response.Status.INTERNAL_SERVER_ERROR.getStatusCode();
		String message = e.getMessage();
		
		if (e instanceof WebApplicationException) {
			WebApplicationException wae = (WebApplicationException) e;
			status = wae.getResponse().getStatus();
		}
		
		return Response
			.status(status)
			.entity(new Error(status, message))
			.build();
	}

}

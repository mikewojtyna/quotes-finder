/**
 * 
 */
package com.slidetorial.qf.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author goobar
 *
 */
/**
 * Handles controller exceptions.
 *
 * @author goobar
 *
 */
@ControllerAdvice
public class ControllerExceptionHandler
{
	private static final Logger LOGGER = LoggerFactory
		.getLogger(ExceptionHandler.class);

	/**
	 * Handles a generic controller exception.
	 *
	 * @param e
	 *                an exception
	 * @return the exception message
	 */
	@ExceptionHandler(Exception.class)
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public ExceptionMessage handleGenericException(Exception e)
	{
		LOGGER.warn(String.format(
			"Generic exception occured. Nested exception: %s",
			e.getMessage()), e);
		return new ExceptionMessage();
	}
}

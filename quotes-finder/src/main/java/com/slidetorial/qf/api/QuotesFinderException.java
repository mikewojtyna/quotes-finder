/**
 *
 */
package com.slidetorial.qf.api;

/**
 * @author goobar
 *
 */
@SuppressWarnings("javadoc")
public class QuotesFinderException extends RuntimeException
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public QuotesFinderException(String message)
	{
		super(message);
	}

	public QuotesFinderException(String message, Throwable cause)
	{
		super(message, cause);
	}

}

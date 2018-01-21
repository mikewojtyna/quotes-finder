/**
 *
 */
package com.slidetorial.qf.repository.importer;

/**
 * @author goobar
 *
 */
@SuppressWarnings("javadoc")
public class QuotesImporterException extends RuntimeException
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public QuotesImporterException(String message)
	{
		super(message);
	}

	public QuotesImporterException(String message, Throwable cause)
	{
		super(message, cause);
	}

}

/**
 *
 */
package com.slidetorial.qf.rest;

/**
 * @author goobar
 *
 */
/**
 * A simple presentation layer exception message produced by controllers.
 *
 * @author goobar
 *
 */
@SuppressWarnings("javadoc")
public class ExceptionMessage
{
	private final String code;

	public ExceptionMessage()
	{
		code = EX_CODE.EX_CODE_DEFAULT.name();
	}

	public ExceptionMessage(EX_CODE code)
	{
		this.code = code.name();
	}

	public ExceptionMessage(String code)
	{
		this.code = code;
	}

	/**
	 * @return the code
	 */
	public String getCode()
	{
		return code;
	}

	public static enum EX_CODE
	{
		EX_CODE_DEFAULT
	}

}

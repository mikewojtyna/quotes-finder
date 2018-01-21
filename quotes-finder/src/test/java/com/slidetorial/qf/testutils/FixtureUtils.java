/**
 *
 */
package com.slidetorial.qf.testutils;

import org.apache.commons.lang3.RandomStringUtils;
import com.slidetorial.qf.matcher.Phrase;
import com.slidetorial.qf.matcher.Quote;

/**
 * @author goobar
 *
 */
@SuppressWarnings("javadoc")
public class FixtureUtils
{

	/**
	 * @param text
	 * @return
	 */
	public static Phrase phrase(String text)
	{
		return new Phrase(text);
	}

	/**
	 * @return
	 */
	public static Phrase randomPhrase()
	{
		return phrase(randomText());
	}

	/**
	 * @return
	 */
	public static Quote randomQuote()
	{
		return new Quote(randomText());
	}

	/**
	 * @return
	 */
	public static String randomText()
	{
		return RandomStringUtils.randomAlphabetic(10);
	}

}

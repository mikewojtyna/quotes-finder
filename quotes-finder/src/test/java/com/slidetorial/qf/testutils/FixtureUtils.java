/**
 *
 */
package com.slidetorial.qf.testutils;

import java.util.UUID;
import org.apache.commons.text.RandomStringGenerator;
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
	 * @param text
	 * @return
	 */
	public static Quote quote(String text)
	{
		return new Quote(randomQuoteId(), text);
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
		return quote(randomText());
	}

	/**
	 * @return
	 */
	public static String randomText()
	{
		return new RandomStringGenerator.Builder().withinRange('a', 'z')
			.build().generate(10);
	}

	/**
	 * @return
	 */
	private static String randomQuoteId()
	{
		return UUID.randomUUID().toString();
	}

}

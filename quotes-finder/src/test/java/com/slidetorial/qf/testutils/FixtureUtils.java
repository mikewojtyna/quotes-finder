/**
 *
 */
package com.slidetorial.qf.testutils;

import org.apache.commons.lang3.RandomStringUtils;
import com.slidetorial.qf.api.Quote;
import com.slidetorial.qf.api.impl.Phrase;

/**
 * @author goobar
 *
 */
@SuppressWarnings("javadoc")
public class FixtureUtils
{

	/**
	 * @return
	 */
	public static Phrase randomPhrase()
	{
		return new Phrase();
	}

	/**
	 * @return
	 */
	public static Quote randomQuote()
	{
		return new Quote(RandomStringUtils.randomAlphabetic(10));
	}

}

/**
 *
 */
package com.slidetorial.qf.api;

import java.util.Collection;
import com.slidetorial.qf.matcher.Quote;

/**
 * A main interface to find all quotes in input text.
 *
 * @author goobar
 *
 */
public interface QuotesFinder
{
	/**
	 * Finds all available quotes in the given text.
	 *
	 * @param text
	 *                a text, might be empty
	 * @return all quotes found inside the text, or an empty collection when
	 *         there's no match (also when input text is empty)
	 * @throws NullPointerException
	 *                 if any argument is null
	 * @throws QuotesFinderException
	 *                 if fails
	 */
	Collection<Quote> find(String text)
		throws NullPointerException, QuotesFinderException;
}

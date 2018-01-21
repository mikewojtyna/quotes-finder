/**
 *
 */
package com.slidetorial.qf.api.impl;

import java.util.Collection;
import com.slidetorial.qf.api.Quote;

/**
 * A component to match phrase against all available quotes. This interface is
 * created to wrap any underlying implementation (e.g. full-text search service)
 * in an uniformed and simple role interface.
 *
 * @author goobar
 *
 */
public interface QuotesPhraseMatcher
{
	/**
	 * Matches a phrase against all available quotes.
	 *
	 * @param phrase
	 *                an input phrase
	 * @return the collection of all matching quotes (empty if there are no
	 *         matching quotes)
	 * @throws NullPointerException
	 *                 if any argument is null
	 * @throws QuotesPhraseMatcherException
	 *                 if fails
	 */
	Collection<Quote> match(Phrase phrase)
		throws NullPointerException, QuotesPhraseMatcherException;
}

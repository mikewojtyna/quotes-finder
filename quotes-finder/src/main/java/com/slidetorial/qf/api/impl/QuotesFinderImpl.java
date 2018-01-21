/**
 *
 */
package com.slidetorial.qf.api.impl;

import static com.google.common.base.Preconditions.checkNotNull;
import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import com.slidetorial.qf.api.QuotesFinder;
import com.slidetorial.qf.api.QuotesFinderException;
import com.slidetorial.qf.matcher.PhraseExtractor;
import com.slidetorial.qf.matcher.Quote;
import com.slidetorial.qf.matcher.QuotesPhraseMatcher;

/**
 * A thin application layer wrapper to coordinate execution of specialized
 * components. Results returned from {@link QuotesPhraseMatcher} are combined
 * together to return the final result.
 *
 * @author goobar
 *
 */
@Service
public class QuotesFinderImpl implements QuotesFinder
{

	private final PhraseExtractor phraseExtractor;

	private final QuotesPhraseMatcher quotesMatcher;

	/**
	 * @param phraseExtractor
	 *                a strategy to extract key phrases from the input text
	 * @param quotesMatcher
	 *                a component to match phrase against all available
	 *                quotes
	 */
	public QuotesFinderImpl(PhraseExtractor phraseExtractor,
		QuotesPhraseMatcher quotesMatcher)
	{
		validate(phraseExtractor, quotesMatcher);
		this.phraseExtractor = phraseExtractor;
		this.quotesMatcher = quotesMatcher;
	}

	/* (non-Javadoc)
	 * @see com.slidetorial.qf.api.QuotesFinder#findQuotes(java.lang.String)
	 */
	@Override
	public Collection<Quote> find(String text)
		throws NullPointerException, QuotesFinderException
	{
		validate(text);
		return phraseExtractor.extract(text).stream()
			.flatMap(phrase -> quotesMatcher.match(phrase).stream())
			.collect(Collectors.toList());
	}

	/**
	 * @param phraseExtractor2
	 * @param quotesMatcher
	 */
	private void validate(PhraseExtractor phraseExtractor,
		QuotesPhraseMatcher quotesMatcher)
	{
		checkNotNull(phraseExtractor,
			"'phraseExtractor' cannot be null");
		checkNotNull(quotesMatcher, "'quotesMatcher' cannot be null");
	}

	/**
	 * @param text
	 */
	private void validate(String text)
	{
		checkNotNull(text, "'text' cannot be null");
	}

}

/**
 *
 */
package com.slidetorial.qf.matcher.impl;

import static com.google.common.base.Preconditions.checkNotNull;
import java.util.Collection;
import java.util.stream.Collectors;
import com.slidetorial.qf.matcher.Phrase;
import com.slidetorial.qf.matcher.Quote;
import com.slidetorial.qf.matcher.QuotesPhraseMatcher;
import com.slidetorial.qf.matcher.QuotesPhraseMatcherException;

/**
 * This implementation works as a decorator over other
 * {@link QuotesPhraseMatcher}. Results returned by configured matcher are then
 * filtered based on the given character percentage threshold. For example, this
 * decorator might limit quotes only to those, where
 * {@code phrase.length / quote.length > 0.8}.
 *
 * @author goobar
 *
 */
public class CharactersThresholdPhraseMatcher implements QuotesPhraseMatcher
{

	private final QuotesPhraseMatcher matcher;

	private final double threshold;

	/**
	 * @param matcher
	 *                a decorated matcher, results returned by this matcher
	 *                will be later filtered based on the given threshold
	 * @param threshold
	 *                a percentage of characters input phrases must match
	 *                with quotes returned by decorated matcher to be
	 *                accepted
	 */
	public CharactersThresholdPhraseMatcher(QuotesPhraseMatcher matcher,
		double threshold)
	{
		validate(matcher, threshold);
		this.matcher = matcher;
		this.threshold = threshold;
	}

	/* (non-Javadoc)
	 * @see com.slidetorial.qf.matcher.QuotesPhraseMatcher#match(com.slidetorial.qf.matcher.Phrase)
	 */
	@Override
	public Collection<Quote> match(Phrase phrase)
		throws NullPointerException, QuotesPhraseMatcherException
	{
		validate(phrase);
		return matcher.match(phrase).stream()
			.filter(quote -> thresholdPredicate(quote, phrase))
			.collect(Collectors.toList());
	}

	/**
	 * @param quote
	 * @param phrase
	 * @return
	 */
	private boolean thresholdPredicate(Quote quote, Phrase phrase)
	{
		double phraseToQuoteCharactersRatio = ((double) phrase.getText()
			.length()) / quote.getSentence().length();
		return phraseToQuoteCharactersRatio >= threshold;
	}

	/**
	 * @param phrase
	 */
	private void validate(Phrase phrase)
	{
		checkNotNull(phrase, "'phrase' cannot be null");
	}

	/**
	 * @param matcher
	 * @param threshold
	 */
	private void validate(QuotesPhraseMatcher matcher, double threshold)
	{
		checkNotNull(matcher, "'matcher' cannot be null");
	}

}

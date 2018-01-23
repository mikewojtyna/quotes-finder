/**
 *
 */
package com.slidetorial.qf.matcher.impl;

import java.util.Collection;
import com.slidetorial.qf.matcher.Phrase;
import com.slidetorial.qf.matcher.Quote;
import com.slidetorial.qf.matcher.QuotesPhraseMatcher;
import com.slidetorial.qf.matcher.QuotesPhraseMatcherException;
import com.slidetorial.qf.repository.QuotesRepository;

/**
 * A thin implementation delegating most of the work to the
 * {@link QuotesRepository}.
 *
 * @author goobar
 *
 */
public class RepositoryQuotesPhraseMatcher implements QuotesPhraseMatcher
{

	private final QuotesRepository repository;

	/**
	 * @param repository
	 *                a repository that will be used to find similar quotes
	 */
	public RepositoryQuotesPhraseMatcher(QuotesRepository repository)
	{
		this.repository = repository;
	}

	/* (non-Javadoc)
	 * @see com.slidetorial.qf.matcher.QuotesPhraseMatcher#match(com.slidetorial.qf.matcher.Phrase)
	 */
	@Override
	public Collection<Quote> match(Phrase phrase)
		throws NullPointerException, QuotesPhraseMatcherException
	{
		return repository.findSimilarQuotes(phrase.getText());
	}

}

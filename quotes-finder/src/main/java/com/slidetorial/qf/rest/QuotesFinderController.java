/**
 *
 */
package com.slidetorial.qf.rest;

import java.util.Collection;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.slidetorial.qf.api.QuotesFinder;
import com.slidetorial.qf.matcher.Quote;

/**
 * A controller to handle quote find requests.
 *
 * @author goobar
 *
 */
@RestController
@RequestMapping("/quotes-finder")
public class QuotesFinderController
{
	private final QuotesFinder finder;

	/**
	 * @param finder
	 *                a quotes finder application layer service to perform
	 *                the operation
	 */
	public QuotesFinderController(QuotesFinder finder)
	{
		this.finder = finder;
	}

	/**
	 * Finds all quotes in given text.
	 *
	 * @param text
	 *                text
	 * @return found quotes
	 */
	@GetMapping
	public Collection<Quote> findQuotes(
		@RequestParam(required = true) String text)
	{
		return finder.find(text);
	}
}

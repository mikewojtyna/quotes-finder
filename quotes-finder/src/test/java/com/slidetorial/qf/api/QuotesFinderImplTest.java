/**
 *
 */
package com.slidetorial.qf.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import com.google.common.testing.NullPointerTester;
import com.slidetorial.qf.api.impl.QuotesFinderImpl;
import com.slidetorial.qf.matcher.Phrase;
import com.slidetorial.qf.matcher.PhraseExtractor;
import com.slidetorial.qf.matcher.Quote;
import com.slidetorial.qf.matcher.QuotesPhraseMatcher;
import com.slidetorial.qf.testutils.FixtureUtils;

/**
 * @author goobar
 *
 */
@SuppressWarnings("javadoc")
public class QuotesFinderImplTest
{

	private PhraseExtractor extractor;

	private QuotesPhraseMatcher matcher;

	private QuotesFinder quotesFinder;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception
	{
		extractor = mock(PhraseExtractor.class);
		matcher = mock(QuotesPhraseMatcher.class);
		quotesFinder = new QuotesFinderImpl(extractor, matcher);
	}

	@Test
	public void should_CombineQuotes_ReturnedByMatcher() throws Exception
	{
		// given
		String text = randomString();
		// configure phrase extractor
		Phrase firstPhrase = randomPhrase();
		Phrase secondPhrase = randomPhrase();
		Phrase thirdPhrase = randomPhrase();
		when(extractor.extract(text)).thenReturn(
			Arrays.asList(firstPhrase, secondPhrase, thirdPhrase));
		// configure quotes matcher
		// there's one matching quote for the first phrase
		Collection<Quote> firstPhraseQuotes = Arrays
			.asList(randomQuote());
		when(matcher.match(firstPhrase)).thenReturn(firstPhraseQuotes);
		// there are two matching quotes for the second phrase
		Collection<Quote> secondPhraseQuotes = Arrays
			.asList(randomQuote(), randomQuote());
		when(matcher.match(secondPhrase))
			.thenReturn(secondPhraseQuotes);
		// there are three matching quotes for the third phrase
		Collection<Quote> thirdPhraseQuotes = Arrays
			.asList(randomQuote(), randomQuote(), randomQuote());
		when(matcher.match(thirdPhrase)).thenReturn(thirdPhraseQuotes);

		// when
		Collection<Quote> quotes = quotesFinder.find(text);

		// then
		// there are a total of 6 quotes returned by the matcher
		assertThat(quotes).hasSize(6);
		assertThat(quotes).containsAll(firstPhraseQuotes);
		assertThat(quotes).containsAll(secondPhraseQuotes);
		assertThat(quotes).containsAll(thirdPhraseQuotes);
	}

	@Test
	public void should_FindQuotes_When_SinglePhraseIsExtracted()
		throws Exception
	{
		// given
		String text = randomString();
		// configure extractor
		Phrase phrase = randomPhrase();
		when(extractor.extract(text)).thenReturn(Arrays.asList(phrase));
		// configure matcher
		Collection<Quote> matchingQuotes = Arrays.asList(randomQuote(),
			randomQuote(), randomQuote());
		when(matcher.match(phrase)).thenReturn(matchingQuotes);

		// when
		Collection<Quote> foundQuotes = quotesFinder.find(text);

		// then
		assertThat(foundQuotes).containsOnlyElementsOf(matchingQuotes);
	}

	@Test
	public void should_Pass_NullTests() throws Exception
	{
		NullPointerTester tester = new NullPointerTester();
		tester.testAllPublicConstructors(QuotesFinder.class);
		tester.testAllPublicInstanceMethods(quotesFinder);
	}

	@Test
	public void should_ReturnEmptyCollection_When_NoPhraseIsExtracted()
		throws Exception
	{
		// given
		String text = randomString();
		// configure extractor
		when(extractor.extract(text))
			.thenReturn(Collections.emptyList());
		// configure matcher
		when(matcher.match(any(Phrase.class)))
			.thenReturn(Arrays.asList(randomQuote()));

		// when
		Collection<Quote> foundQuotes = quotesFinder.find(text);

		// then
		assertThat(foundQuotes).isEmpty();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception
	{
	}

	/**
	 * @return
	 */
	private Phrase randomPhrase()
	{
		return FixtureUtils.randomPhrase();
	}

	/**
	 * @return
	 */
	private Quote randomQuote()
	{
		return FixtureUtils.randomQuote();
	}

	/**
	 * @return
	 */
	private String randomString()
	{
		return RandomStringUtils.randomAlphabetic(10);
	}

}

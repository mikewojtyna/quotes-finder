/**
 *
 */
package com.slidetorial.qf.matcher;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.Collection;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.slidetorial.qf.IntegrationTestMarker;
import com.slidetorial.qf.repository.QuotesRepository;
import com.slidetorial.qf.testutils.FixtureUtils;

/**
 * @author goobar
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Category(IntegrationTestMarker.class)
@SuppressWarnings("javadoc")
public class RepositoryQuotesPhraseMatcherIntegrationTest
{
	@Autowired
	@Qualifier("repositoryMatcher")
	private QuotesPhraseMatcher matcher;

	@Autowired
	private QuotesRepository repository;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception
	{
		repository.deleteAll();
	}

	@Test
	public void should_FindExactlyMatchingQuote() throws Exception
	{
		// given
		String text = "One of the advantages of being disorderly is that one is constantly making exciting discoveries.";
		Phrase exactPhrase = phrase(text);
		Quote expectedQuote = quote(text);
		// populate repository
		// ... with matching quote
		repository.save(expectedQuote);
		// ... and with some fake quotes
		repository.save(quote(randomText()));
		repository.save(quote(randomText()));

		// when
		Collection<Quote> matchingQuotes = matcher.match(exactPhrase);

		// then
		assertThat(matchingQuotes).containsOnly(expectedQuote);
	}

	@Test
	public void should_FindMultipleSimilarQuotes() throws Exception
	{
		// given
		Phrase similarPhrase = phrase(
			"ONE of the _AdVaNtaGES_ Of!       BEiNG DisoRDerLy is that constantly MaKING EXCiting");
		// expected quotes
		Quote firstExpectedQuote = quote(
			"One of the advantages of being disorderly is that one is constantly making exciting discoveries.");
		Quote secondExpectedQuote = quote(
			"Prefix One of the advantages of being disorderly is that constantly making exciting discoveries Suffix");
		Quote thirdExpectedQuote = quote(
			"One 111 of the advantages of being _hello_ disorderly is that constantly making exciting truly discoveries");
		// populate repository
		// ... with matching quotes
		repository.save(firstExpectedQuote);
		repository.save(secondExpectedQuote);
		repository.save(thirdExpectedQuote);
		// ... and with some fake quotes
		repository.save(quote(randomText()));
		repository.save(quote(randomText()));
		repository.save(quote(randomText()));

		// when
		Collection<Quote> matchingQuotes = matcher.match(similarPhrase);

		// then
		assertThat(matchingQuotes).containsExactlyInAnyOrder(
			firstExpectedQuote, secondExpectedQuote,
			thirdExpectedQuote);
	}

	@Test
	public void should_FindSimilarQuote() throws Exception
	{
		// given
		Quote expectedQuote = quote(
			"One of the advantages of being disorderly is that one is constantly making exciting discoveries.");
		Phrase similarPhrase = phrase(
			"ONE of the _AdVaNtaGES_ Of!       BEiNG DisoRDerLy is that constantly MaKING EXCiting");
		// populate repository
		// ... with matching quote
		repository.save(expectedQuote);
		// ... and with some fake quotes
		repository.save(quote(randomText()));
		repository.save(quote(randomText()));

		// when
		Collection<Quote> matchingQuotes = matcher.match(similarPhrase);

		// then
		assertThat(matchingQuotes).containsOnly(expectedQuote);
	}

	@Test
	public void should_NotFindAnyQuote_When_PhraseWordProximityIsGreaterThan2()
		throws Exception
	{
		// given
		Quote expectedQuote = quote(
			"One of the advantages of being disorderly is that one is constantly making exciting discoveries.");
		Phrase similarPhrase = phrase(
			"One of the advantages disorderly is constantly making exciting discoveries");
		// populate repository
		// ... with matching quote
		repository.save(expectedQuote);
		// ... and with some fake quotes
		repository.save(quote(randomText()));
		repository.save(quote(randomText()));

		// when
		Collection<Quote> matchingQuotes = matcher.match(similarPhrase);

		// then
		assertThat(matchingQuotes).isEmpty();
	}

	@Test
	public void should_ReturnStoredAuthor() throws Exception
	{
		// given
		String text = "One of the advantages of being disorderly is that one is constantly making exciting discoveries.";
		Phrase exactPhrase = phrase(text);
		String author = "A. A. Milne";
		// populate repository
		repository.save(quote(text, author));

		// when
		Collection<Quote> matchingQuotes = matcher.match(exactPhrase);

		// then
		assertThat(matchingQuotes.iterator().next().getAuthor())
			.isEqualTo(author);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception
	{
	}

	/**
	 * @param sentence
	 * @return
	 */
	private Phrase phrase(String sentence)
	{
		return FixtureUtils.phrase(sentence);
	}

	/**
	 * @param author
	 * @param sentence
	 * @return
	 */
	private Quote quote(String sentence)
	{
		return FixtureUtils.quote(sentence);
	}

	/**
	 * @param author
	 * @param sentence
	 * @return
	 */
	private Quote quote(String sentence, String author)
	{
		return FixtureUtils.quote(sentence, author);
	}

	/**
	 * @return
	 */
	private String randomText()
	{
		return FixtureUtils.randomText();
	}

}

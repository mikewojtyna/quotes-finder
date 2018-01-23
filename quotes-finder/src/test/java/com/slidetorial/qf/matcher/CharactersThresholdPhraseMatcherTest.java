/**
 *
 */
package com.slidetorial.qf.matcher;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import com.google.common.testing.NullPointerTester;
import com.slidetorial.qf.matcher.impl.CharactersThresholdPhraseMatcher;
import com.slidetorial.qf.testutils.FixtureUtils;

/**
 * @author goobar
 *
 */
@SuppressWarnings("javadoc")
public class CharactersThresholdPhraseMatcherTest
{

	private QuotesPhraseMatcher decoratedPhraseMatcher;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception
	{
		decoratedPhraseMatcher = mock(QuotesPhraseMatcher.class);
	}

	@Test
	public void should_AcceptQuote_If_06_ThresholdIsPassed()
		throws Exception
	{
		// given
		double threshold = 0.6;
		QuotesPhraseMatcher thresholdPhraseMatcher = new CharactersThresholdPhraseMatcher(
			decoratedPhraseMatcher, threshold);
		// input phrase is 30 characters long
		Phrase inputPhrase = phrase("Weeds flowers, once you get to");
		// quote returned by decorated phrase matcher
		// decorated quote is 48 characters long
		Quote decoratedQuote = quote(
			"Weeds are flowers too, once you get to know them");
		when(decoratedPhraseMatcher.match(inputPhrase))
			.thenReturn(Arrays.asList(decoratedQuote));

		// when
		Collection<Quote> quotes = thresholdPhraseMatcher
			.match(inputPhrase);

		// then
		// phrase/quote = 30 / 48 > 0.62 => threshold (0.6) is passed,
		// decorated quote is therefore accepted
		assertThat(quotes).containsOnly(decoratedQuote);
	}

	@Test
	public void should_AcceptQuote_If_ThresholdIsExactlyPassed()
		throws Exception
	{
		// given
		double threshold = 0.8;
		QuotesPhraseMatcher thresholdPhraseMatcher = new CharactersThresholdPhraseMatcher(
			decoratedPhraseMatcher, threshold);
		// input phrase is 8 characters long
		Phrase inputPhrase = phrase("one two!");
		// quote returned by decorated phrase matcher
		// decorated quote is 10 characters long
		Quote decoratedQuote = quote("one two yo");
		when(decoratedPhraseMatcher.match(inputPhrase))
			.thenReturn(Arrays.asList(decoratedQuote));

		// when
		Collection<Quote> quotes = thresholdPhraseMatcher
			.match(inputPhrase);

		// then
		// phrase/quote = 8 / 10 = 0.8 (exactly) => threshold (0.8) is
		// passed,
		// decorated quote is accepted
		assertThat(quotes).containsOnly(decoratedQuote);
	}

	@Test
	public void should_AcceptQuote_If_ThresholdIsPassed() throws Exception
	{
		// given
		double threshold = 0.8;
		QuotesPhraseMatcher thresholdPhraseMatcher = new CharactersThresholdPhraseMatcher(
			decoratedPhraseMatcher, threshold);
		// input phrase is 40 characters long
		Phrase inputPhrase = phrase(
			"Weeds flowers, once you get to know them");
		// quote returned by decorated phrase matcher
		// decorated quote is 48 characters long
		Quote decoratedQuote = quote(
			"Weeds are flowers too, once you get to know them");
		when(decoratedPhraseMatcher.match(inputPhrase))
			.thenReturn(Arrays.asList(decoratedQuote));

		// when
		Collection<Quote> quotes = thresholdPhraseMatcher
			.match(inputPhrase);

		// then
		// phrase/quote = 40 / 48 > 0.83 => threshold (0.8) is passed,
		// decorated quote
		// is accepted
		assertThat(quotes).containsOnly(decoratedQuote);
	}

	@Test
	public void should_FilterQuotes_Where_ThresholdIsPassed()
		throws Exception
	{
		// given
		double threshold = 0.8;
		QuotesPhraseMatcher thresholdPhraseMatcher = new CharactersThresholdPhraseMatcher(
			decoratedPhraseMatcher, threshold);
		// input phrase is 40 characters long
		Phrase inputPhrase = phrase(
			"Weeds flowers, once you get to know them");
		// quote returned by decorated phrase matcher
		// "good" quotes >= threshold
		// 48 characters long
		Quote goodQuote0 = quote(
			"Weeds are flowers too, once you get to know them");
		// 40 characters long
		Quote goodQuote1 = quote(
			"Weeds are too, once you get to know them");
		// 43 characters long
		Quote goodQuote2 = quote(
			"Weeds are flowers too, once you get to know");
		// "bad" quotes < threshold (too long quotes)
		// 58 characters long
		Quote badQuote0 = quote(
			"Weeds are beautiful flowers too, once you get to know them");
		// 63 characters long
		Quote badQuote1 = quote(
			"Weeds are very beautiful flowers too, once you get to know them");
		// 69 characters long
		Quote badQuote2 = quote(
			"Weeds are so totally beautiful flowers too, once you get to know them");
		when(decoratedPhraseMatcher.match(inputPhrase))
			.thenReturn(Arrays.asList(goodQuote0, badQuote0,
				badQuote1, goodQuote1, badQuote2, goodQuote2));

		// when
		Collection<Quote> quotes = thresholdPhraseMatcher
			.match(inputPhrase);

		// then
		// only "good" quotes pass the threshold
		assertThat(quotes).containsOnly(goodQuote0, goodQuote1,
			goodQuote2);
	}

	@Test
	public void should_Pass_NullTests() throws Exception
	{
		NullPointerTester tester = new NullPointerTester();
		tester.testAllPublicConstructors(
			CharactersThresholdPhraseMatcher.class);
		tester.testAllPublicInstanceMethods(instance());
	}

	@Test
	public void should_RejectQuote_If_ThresholdIsNotPassed()
		throws Exception
	{
		// given
		double threshold = 0.8;
		QuotesPhraseMatcher thresholdPhraseMatcher = new CharactersThresholdPhraseMatcher(
			decoratedPhraseMatcher, threshold);
		// input phrase is 30 characters long
		Phrase inputPhrase = phrase("Weeds flowers, once you get to");
		// quote returned by decorated phrase matcher
		// decorated quote is 48 characters long
		Quote decoratedQuote = quote(
			"Weeds are flowers too, once you get to know them");
		when(decoratedPhraseMatcher.match(inputPhrase))
			.thenReturn(Arrays.asList(decoratedQuote));

		// when
		Collection<Quote> quotes = thresholdPhraseMatcher
			.match(inputPhrase);

		// then
		// phrase/quote = 30 / 48 > 0.62 => threshold (0.8) is not
		// passed, decorated quote is therefore rejected
		assertThat(quotes).isEmpty();
	}

	@Test
	public void should_ReturnEmptyCollection_When_DecoratedMatcherMatchesNoQuotes()
		throws Exception
	{
		// given
		double threshold = 0.8;
		QuotesPhraseMatcher thresholdPhraseMatcher = new CharactersThresholdPhraseMatcher(
			decoratedPhraseMatcher, threshold);
		Phrase inputPhrase = phrase("Weeds flowers, once you get to");
		// decorated matcher returns empty collection
		when(decoratedPhraseMatcher.match(inputPhrase))
			.thenReturn(Collections.emptyList());

		// when
		Collection<Quote> quotes = thresholdPhraseMatcher
			.match(inputPhrase);

		// then
		assertThat(quotes).isEmpty();
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
	private Object instance()
	{
		return new CharactersThresholdPhraseMatcher(
			decoratedPhraseMatcher, 0.8);
	}

	/**
	 * @param text
	 * @return
	 */
	private Phrase phrase(String text)
	{
		return FixtureUtils.phrase(text);
	}

	/**
	 * @param sentence
	 * @return
	 */
	private Quote quote(String sentence)
	{
		return FixtureUtils.quote(sentence);
	}

}

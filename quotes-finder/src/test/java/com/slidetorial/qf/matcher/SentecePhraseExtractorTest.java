/**
 *
 */
package com.slidetorial.qf.matcher;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.Collection;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import com.google.common.testing.NullPointerTester;
import com.slidetorial.qf.matcher.impl.SentenceExtractor;
import com.slidetorial.qf.testutils.FixtureUtils;

/**
 * @author goobar
 *
 */
@SuppressWarnings("javadoc")
public class SentecePhraseExtractorTest
{

	private PhraseExtractor extractor;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception
	{
		extractor = new SentenceExtractor();
	}

	@Test
	public void should_ExtractEachSentence_Without_LeadingAndTrailingWhitespaces()
		throws Exception
	{
		// given
		String text = " This is the first sentence    . This is the second sentence   .";

		// when
		Collection<Phrase> phrases = extractor.extract(text);

		// then
		assertThat(phrases).containsOnly(
			phrase("This is the first sentence"),
			phrase("This is the second sentence"));
	}

	@Test
	public void should_ExtractManySentences() throws Exception
	{
		// given
		String text = "First. Second. Third. Fourth. Fifth. Sixth.";

		// when
		Collection<Phrase> phrases = extractor.extract(text);

		// then
		assertThat(phrases).containsOnly(phrase("First"),
			phrase("Second"), phrase("Third"), phrase("Fourth"),
			phrase("Fifth"), phrase("Sixth"));
	}

	@Test
	public void should_ExtractOnlySubstringBeforeDot_When_TextEndsWithDot()
		throws Exception
	{
		// given
		String text = "This text ends with.";

		// when
		Collection<Phrase> phrases = extractor.extract(text);

		// then
		assertThat(phrases).containsOnly(phrase("This text ends with"));
	}

	@Test
	public void should_ExtractTwoSentences_When_TextContainsSeparatingDot()
		throws Exception
	{
		// given
		String text = "This is the first sentence.This is the second sentence";

		// when
		Collection<Phrase> phrases = extractor.extract(text);

		// then
		assertThat(phrases).containsOnly(
			phrase("This is the first sentence"),
			phrase("This is the second sentence"));
	}

	@Test
	public void should_IgnoreWhitespaceOnlyPhrases() throws Exception
	{
		// given
		String text = "This is the first sentence. . This is the second sentence. .";

		// when
		Collection<Phrase> phrases = extractor.extract(text);

		// then
		assertThat(phrases).containsOnly(
			phrase("This is the first sentence"),
			phrase("This is the second sentence"));
	}

	@Test
	public void should_Pass_NullTests() throws Exception
	{
		NullPointerTester tester = new NullPointerTester();
		tester.testAllPublicConstructors(SentenceExtractor.class);
		tester.testAllPublicInstanceMethods(extractor);
	}

	@Test
	public void should_ReturnEmptyCollection_When_TextContainsOnlyDots()
		throws Exception
	{
		// given
		String text = "...";

		// when
		Collection<Phrase> phrases = extractor.extract(text);

		// then
		assertThat(phrases).isEmpty();
	}

	@Test
	public void should_ReturnEmptyCollection_When_TextContainsOnlyDotsAndWhitespaces()
		throws Exception
	{
		// given
		String text = "   .   . ..  .   ";

		// when
		Collection<Phrase> phrases = extractor.extract(text);

		// then
		assertThat(phrases).isEmpty();
	}

	@Test
	public void should_ReturnInputString_When_ThereIsNoDotCharacter()
		throws Exception
	{
		// given
		String text = "this is a text without dot characted";

		// when
		Collection<Phrase> phrases = extractor.extract(text);

		// then
		assertThat(phrases).containsOnly(phrase(text));
	}

	@Test
	public void should_TrimLeadingAndTrailingWhitespaces_When_ThereIsNoDotCharacter()
		throws Exception
	{
		// given
		String text = "    This is the first sentence      ";

		// when
		Collection<Phrase> phrases = extractor.extract(text);

		// then
		assertThat(phrases)
			.containsOnly(phrase("This is the first sentence"));
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception
	{
	}

	/**
	 * @param text
	 * @return
	 */
	private Phrase phrase(String text)
	{
		return FixtureUtils.phrase(text);
	}

}

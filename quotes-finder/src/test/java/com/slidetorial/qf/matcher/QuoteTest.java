/**
 *
 */
package com.slidetorial.qf.matcher;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import com.google.common.testing.NullPointerTester;
import com.slidetorial.qf.matcher.Quote;
import nl.jqno.equalsverifier.EqualsVerifier;

/**
 * @author goobar
 *
 */
@SuppressWarnings("javadoc")
public class QuoteTest
{

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception
	{
	}

	@Test
	public void should_CreateInstance() throws Exception
	{
		// given
		String sentence = randomSentence();

		// when
		Quote quote = new Quote(sentence);

		// then
		assertThat(quote.getSentence()).isEqualTo(sentence);
	}

	@Test
	public void should_Pass_EqualsTests() throws Exception
	{
		EqualsVerifier.forClass(Quote.class).usingGetClass().verify();
	}

	@Test
	public void should_Pass_NullTests() throws Exception
	{
		NullPointerTester tester = new NullPointerTester();
		tester.testAllPublicConstructors(Quote.class);
		tester.testAllPublicInstanceMethods(instance());
	}

	@Test
	public void should_ToString_ReturnNonEmptyString() throws Exception
	{
		assertFalse(instance().toString().isEmpty());
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
		return new Quote(randomSentence());
	}

	/**
	 * @return
	 */
	private String randomSentence()
	{
		return RandomStringUtils.randomAlphabetic(10);
	}

}

/**
 *
 */
package com.slidetorial.qf.matcher;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import com.google.common.testing.NullPointerTester;
import com.slidetorial.qf.testutils.FixtureUtils;
import nl.jqno.equalsverifier.EqualsVerifier;

/**
 * @author goobar
 *
 */
@SuppressWarnings("javadoc")
public class PhraseTest
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
		String text = FixtureUtils.randomText();

		// when
		Phrase phrase = new Phrase(text);

		// then
		assertThat(phrase.getText()).isEqualTo(text);
	}

	@Test
	public void should_Pass_EqualsTests() throws Exception
	{
		EqualsVerifier.forClass(Phrase.class).usingGetClass().verify();
	}

	@Test
	public void should_Pass_NullTests() throws Exception
	{
		NullPointerTester tester = new NullPointerTester();
		tester.testAllPublicConstructors(Phrase.class);
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
		return FixtureUtils.randomPhrase();
	}

}

/**
 *
 */
package com.slidetorial.qf.api;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.Collection;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.slidetorial.qf.IntegrationTestMarker;
import com.slidetorial.qf.matcher.Quote;
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
public class QuotesFinderIntegrationTest
{
	@Autowired
	private QuotesFinder finder;

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
	public void should_FindQuotes() throws Exception
	{
		// given
		String inputText = "This is first irrelevant sentence. And then they all died. This is another irrelevant sentence.";
		Quote expectedQuote = FixtureUtils
			.quote("And then they all died", "George Martin");
		repository.save(expectedQuote);
		// add some random quotes
		repository.save(randomQuote());
		repository.save(randomQuote());

		// when
		Collection<Quote> quotes = finder.find(inputText);

		// then
		assertThat(quotes).containsOnly(expectedQuote);
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
	private Quote randomQuote()
	{
		return FixtureUtils.randomQuote();
	}

}

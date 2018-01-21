/**
 *
 */
package com.slidetorial.qf.repository.importer;

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

/**
 * @author goobar
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Category(IntegrationTestMarker.class)
@SuppressWarnings("javadoc")
public class ClasspathTxtQuotesImporterIntegrationTest
{
	@Autowired
	private QuotesImporter importer;

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
	public void should_ImportAllQuotesFromClasspath() throws Exception
	{
		// when
		importer.importQuotes();

		// then
		assertThat(repository.findAll()).hasSize(36165);
	}

	@Test
	public void should_SplitAuthorAndSentence() throws Exception
	{
		// when
		importer.importQuotes();

		// then
		// values taken from "quotes.txt" file
		Collection<Quote> similarQuotes = repository.findSimilarQuotes(
			"Did you ever stop to think, and forget to start again?");
		assertThat(similarQuotes).hasSize(1);
		Quote similarQuote = similarQuotes.iterator().next();
		assertThat(similarQuote.getAuthor()).isEqualTo("A. A. Milne");
		assertThat(similarQuote.getSentence()).isEqualTo(
			"Did you ever stop to think, and forget to start again?");
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception
	{
	}

}

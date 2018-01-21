/**
 *
 */
package com.slidetorial.qf.rest;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.Arrays;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import com.slidetorial.qf.IntegrationTestMarker;
import com.slidetorial.qf.api.QuotesFinder;
import com.slidetorial.qf.api.QuotesFinderException;
import com.slidetorial.qf.matcher.Quote;
import com.slidetorial.qf.testutils.FixtureUtils;

/**
 * @author goobar
 *
 */
@RunWith(SpringRunner.class)
@WebMvcTest
@Category(IntegrationTestMarker.class)
@SuppressWarnings("javadoc")
public class QuotesFinderControllerIntegrationTest
{
	@MockBean
	private QuotesFinder finder;

	@Autowired
	private MockMvc mockMvc;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception
	{
	}

	@Test
	public void should_FindQuotes() throws Exception
	{
		// given
		String text = "First sentence. And then they all died. Third sentence.";
		Quote foundQuote = quote("And then they all died",
			"George Martin");
		// configure query finder
		when(finder.find(text)).thenReturn(Arrays.asList(foundQuote));

		// when
		mockMvc.perform(get("/quotes-finder").param("text", text))
			// then
			.andExpect(status().isOk())
			.andExpect(jsonPath("$", hasSize(1)))
			.andExpect(jsonPath("$[0].sentence",
				is(foundQuote.getSentence())))
			.andExpect(jsonPath("$[0].author",
				is(foundQuote.getAuthor())));
	}

	@Test
	public void should_HandleException() throws Exception
	{
		// given
		String text = "First sentence. And then they all died. Third sentence.";
		// configure query finder to fail
		when(finder.find(text)).thenThrow(
			new QuotesFinderException("query finder failed"));

		// when
		mockMvc.perform(get("/quotes-finder").param("text", text))
			// then
			.andExpect(status().isInternalServerError())
			.andExpect(jsonPath("$.code",
				is(ExceptionMessage.EX_CODE.EX_CODE_DEFAULT
					.name())));
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
	 * @param author
	 * @return
	 */
	private Quote quote(String sentence, String author)
	{
		return FixtureUtils.quote(sentence, author);
	}

}

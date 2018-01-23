/**
 *
 */
package com.slidetorial.qf.matcher;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.slidetorial.qf.IntegrationTestMarker;
import com.slidetorial.qf.matcher.impl.CharactersThresholdPhraseMatcher;

/**
 * @author goobar
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Category(IntegrationTestMarker.class)
@SuppressWarnings("javadoc")
public class PrimaryQuotesPhraseMatcherIntegrationTest
{
	@Autowired
	private QuotesPhraseMatcher matcher;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception
	{
	}

	@Test
	public void should_UseThresholdPhraseMatcherAsPrimaryBean()
		throws Exception
	{
		assertThat(matcher)
			.isInstanceOf(CharactersThresholdPhraseMatcher.class);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception
	{
	}

}

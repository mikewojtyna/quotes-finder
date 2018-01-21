/**
 *
 */
package com.slidetorial.qf.cli;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.DefaultApplicationArguments;
import com.slidetorial.qf.cli.QuotesImporterCliRunner;
import com.slidetorial.qf.repository.importer.QuotesImporter;

/**
 * @author goobar
 *
 */
@SuppressWarnings("javadoc")
public class QuotesImporterCliRunnerTest
{

	private QuotesImporter importer;

	private QuotesImporterCliRunner runner;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception
	{
		importer = mock(QuotesImporter.class);
		runner = new QuotesImporterCliRunner(importer);
	}

	@Test
	public void should_NotRunQuotesImporter_When_ImportQuotesArgIsNotGiven()
		throws Exception
	{
		// given
		ApplicationArguments args = new DefaultApplicationArguments(
			new String[] {});

		// when
		runner.run(args);

		// then
		// generally I prefer the classicists approach, but this case is
		// perfect to apply the mockists approach
		verifyZeroInteractions(importer);
	}

	@Test
	public void should_RunQuotesImporter_When_ImportQuotesArgIsGiven()
		throws Exception
	{
		// given
		ApplicationArguments args = new DefaultApplicationArguments(
			new String[] { "--import-quotes" });

		// when
		runner.run(args);

		// then
		// generally I prefer the classicists approach, but this case is
		// perfect to apply the mockists approach
		verify(importer, times(1)).importQuotes();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception
	{
	}

}

/**
 *
 */
package com.slidetorial.qf.cli;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.DefaultApplicationArguments;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.slidetorial.qf.IntegrationTestMarker;

/**
 * @author goobar
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Category(IntegrationTestMarker.class)
@SuppressWarnings("javadoc")
public class QuotesImporterCliRunnerIntegrationTest
{
	@Autowired
	private QuotesImporterCliRunner runner;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception
	{
	}

	@Test
	public void should_RunRealRunnerWithoutExceptions() throws Exception
	{
		runner.run(new DefaultApplicationArguments(
			new String[] { "--import-quotes" }));
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception
	{
	}

}

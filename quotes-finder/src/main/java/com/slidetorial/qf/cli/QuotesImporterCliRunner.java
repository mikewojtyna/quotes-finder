/**
 *
 */
package com.slidetorial.qf.cli;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import com.slidetorial.qf.repository.importer.QuotesImporter;

/**
 * A command line interface handler to run the quotes importer.
 *
 * @author goobar
 *
 */
@Component
public class QuotesImporterCliRunner implements ApplicationRunner
{

	private final QuotesImporter importer;

	/**
	 * @param importer
	 *                the importer strategy used to import quotes into the
	 *                db
	 */
	public QuotesImporterCliRunner(QuotesImporter importer)
	{
		this.importer = importer;
	}

	/* (non-Javadoc)
	 * @see org.springframework.boot.ApplicationRunner#run(org.springframework.boot.ApplicationArguments)
	 */
	@Override
	public void run(ApplicationArguments args) throws Exception
	{
		if (args.containsOption("import-quotes"))
		{
			importer.importQuotes();
		}
	}

}

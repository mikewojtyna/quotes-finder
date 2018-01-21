/**
 *
 */
package com.slidetorial.qf.repository.importer.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.text.MessageFormat;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import com.slidetorial.qf.matcher.Quote;
import com.slidetorial.qf.repository.QuotesRepository;
import com.slidetorial.qf.repository.importer.QuotesImporter;
import com.slidetorial.qf.repository.importer.QuotesImporterException;

/**
 * An importer which searches the classpath and tries to find "quotes.txt" text
 * file. Each line contains author and quote separated by tab character.
 *
 * @author goobar
 * @implNote Generally speaking we could create much better design of importer.
 *           One approach might be the following design: create another
 *           component called QuotesDataSource (implemented e.g. by
 *           ClasspathQuotesDataSource or RestQuotesDataSource (taking values
 *           from public REST API) ), which will provide all available quote
 *           lines. This component will be later used by QuotesImporter which
 *           will contain only the logic required to parse each line. The
 *           proposed design is much cleaner, because it doesn't violate SRP,
 *           and we also receive much cleaner separation and testability (e.g.
 *           we can test against empty lines or exceptions thrown by
 *           QuotesDataSource). However, quotes importer is a very simple
 *           component, without any domain logic and works just as a
 *           infrastructure glue code that will be used probably just once.
 *           That's why it's acceptable to follow the current design. It's
 *           simply not worth (in business terms) to give it any more attention.
 *           If it's ever required to dynamically import new quotes, e.g. as
 *           part of the public API, then the previous design would become
 *           immediately mandatory and a refactor would be needed.
 *
 */
@Component
public class ClasspathTxtQuotesImporter implements QuotesImporter
{

	private final QuotesRepository repository;

	/**
	 * @param repository
	 *                a quotes repository that will be used to store all
	 *                imported quotes
	 */
	public ClasspathTxtQuotesImporter(QuotesRepository repository)
	{
		this.repository = repository;
	}

	/* (non-Javadoc)
	 * @see com.slidetorial.qf.repository.importer.QuotesImporter#importQuotes()
	 */
	@Override
	public void importQuotes() throws QuotesImporterException
	{
		try (BufferedReader reader = new BufferedReader(
			new InputStreamReader(
				getClass().getResourceAsStream("/quotes.txt"),
				Charset.forName("UTF-8"))))
		{
			repository.save(reader.lines().parallel().map(line -> {
				String[] authorAndSentence = line.split("\\t");
				return new Quote(generateId(),
					authorAndSentence[1],
					authorAndSentence[0]);
			}).collect(Collectors.toList()));
		}
		catch (IOException e)
		{
			throw new QuotesImporterException(MessageFormat.format(
				"Failed to import quotes. Reason: {0}.",
				e.getMessage()), e);
		}
	}

	/**
	 * @return
	 */
	private String generateId()
	{
		// TODO: probably it's a better idea to create a separate
		// QuoteIdFactory component
		return UUID.randomUUID().toString();
	}

}

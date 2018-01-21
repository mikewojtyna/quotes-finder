/**
 *
 */
package com.slidetorial.qf.repository.importer;

/**
 * A strategy interface to import quotes into the database.
 *
 * @author goobar
 *
 */
public interface QuotesImporter
{
	/**
	 * Imports quotes into the database.
	 *
	 * @throws QuotesImporterException
	 *                 if fails
	 */
	void importQuotes() throws QuotesImporterException;
}

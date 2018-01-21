/**
 *
 */
package com.slidetorial.qf.matcher;

import java.util.Collection;

/**
 * A strategy to extract key phrases from input text.
 *
 * @author goobar
 *
 */
public interface PhraseExtractor
{
	/**
	 * Extracts key phrases from input text.
	 *
	 * @param text
	 *                a text, might be empty
	 * @return collection containing all key phrases, or an empty collection
	 *         if text is empty
	 * @throws NullPointerException
	 *                 if any argument is null
	 * @throws PhraseExtractorException
	 *                 if fails
	 */
	Collection<Phrase> extract(String text)
		throws NullPointerException, PhraseExtractorException;
}

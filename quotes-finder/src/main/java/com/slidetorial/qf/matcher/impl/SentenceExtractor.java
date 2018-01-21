/**
 *
 */
package com.slidetorial.qf.matcher.impl;

import static com.google.common.base.Preconditions.checkNotNull;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import com.slidetorial.qf.matcher.Phrase;
import com.slidetorial.qf.matcher.PhraseExtractor;
import com.slidetorial.qf.matcher.PhraseExtractorException;

/**
 * This extractor splits input text into sentences. It's assumed sentences are
 * separated by "." characters. This is just a very first approach to extract
 * key phrases from the input text. Most probably it's not the best solution,
 * and a more elaborate technique should be used instead. Thanks to the right
 * interfaces segregation, we can easily exchange extractors simply by creating
 * new implementations of {@link PhraseExtractor} strategy.
 *
 * @implNote If there's a need to have more than one extractor splitting the
 *           input string based on the arbitrary character/string delimiter,
 *           it's a good idea to create a generic extractor template used by
 *           every implementation or use a factory to configure the right
 *           implementation based on the given delimiter.
 *
 * @author goobar
 *
 */
@Component
public class SentenceExtractor implements PhraseExtractor
{

	/* (non-Javadoc)
	 * @see com.slidetorial.qf.matcher.PhraseExtractor#extract(java.lang.String)
	 */
	@Override
	public Collection<Phrase> extract(String text)
		throws NullPointerException, PhraseExtractorException
	{
		validate(text);
		return Stream.of(text.split("\\.")).filter(this::notEmpty)
			.map(this::phrase).collect(Collectors.toList());
	}

	private boolean notEmpty(String text)
	{
		return !StringUtils.isWhitespace(text);
	}

	private Phrase phrase(String text)
	{
		return new Phrase(text.trim());
	}

	/**
	 * @param text
	 */
	private void validate(String text)
	{
		checkNotNull(text, "'text' cannot be null");
	}

}

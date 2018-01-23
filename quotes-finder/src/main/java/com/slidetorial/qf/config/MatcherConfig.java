/**
 *
 */
package com.slidetorial.qf.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import com.slidetorial.qf.matcher.QuotesPhraseMatcher;
import com.slidetorial.qf.matcher.impl.CharactersThresholdPhraseMatcher;
import com.slidetorial.qf.matcher.impl.RepositoryQuotesPhraseMatcher;
import com.slidetorial.qf.repository.QuotesRepository;

/**
 * A {@link QuotesPhraseMatcher} config.
 *
 * @author goobar
 *
 */
@Configuration
public class MatcherConfig
{
	private static final double THRESHOLD = 0.8;

	/**
	 * @param repository
	 *                a quotes repository
	 * @return the instance of {@link RepositoryQuotesPhraseMatcher}
	 */
	@Bean(name = "repositoryMatcher")
	public QuotesPhraseMatcher repositoryMatcher(
		QuotesRepository repository)
	{
		return new RepositoryQuotesPhraseMatcher(repository);
	}

	/**
	 * @param matcher
	 *                a decorated matcher
	 * @return the instance of {@link CharactersThresholdPhraseMatcher}
	 */
	@Bean(name = "thresholdMatcher")
	@Primary
	public QuotesPhraseMatcher thresholdMatcher(QuotesPhraseMatcher matcher)
	{
		return new CharactersThresholdPhraseMatcher(matcher, THRESHOLD);
	}
}

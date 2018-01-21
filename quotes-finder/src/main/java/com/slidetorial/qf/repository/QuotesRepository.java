/**
 *
 */
package com.slidetorial.qf.repository;

import java.util.Collection;
import org.springframework.data.solr.repository.Query;
import org.springframework.data.solr.repository.SolrCrudRepository;
import com.slidetorial.qf.matcher.Quote;

/**
 * A Solr quotes repository. This repository is used mainly to find similar
 * quotes. Currently, an extremely simple approach is used - a hardcoded word
 * proximity of 2 is used. To make this repository production-ready, we should
 * probably employ more elaborated approach, e.g. based on the quote/phrase
 * length percentage values. However, for now this solution will suffice for our
 * demo needs.
 *
 * @author goobar
 *
 */
public interface QuotesRepository extends SolrCrudRepository<Quote, String>
{
	/**
	 * Finds quotes similar to the given phrase. By "similar" we mean all
	 * quotes with word proximity search no greater than 2. However, the
	 * exact result depends entirely on the Solr configuration (used types,
	 * tokenizers, filters etc).
	 *
	 * @param phrase
	 * @return the collection containing quotes similar to the given phrase
	 */
	@Query("sentence:\"?0\"~2")
	Collection<Quote> findSimilarQuotes(String phrase);
}

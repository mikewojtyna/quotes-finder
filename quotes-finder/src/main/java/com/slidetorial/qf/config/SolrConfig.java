/**
 *
 */
package com.slidetorial.qf.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.repository.config.EnableSolrRepositories;
import org.springframework.data.solr.server.SolrClientFactory;
import org.springframework.data.solr.server.support.EmbeddedSolrServerFactory;
import com.slidetorial.qf.RootPackageMarker;

/**
 * A Java config for Solr infrastructure.
 *
 * @author goobar
 *
 */
@EnableSolrRepositories(basePackageClasses = RootPackageMarker.class)
@Configuration
public class SolrConfig
{
	/**
	 * @return the solr client
	 * @throws Exception
	 *                 if fails
	 */
	@Bean
	public SolrClientFactory solrClientFactory() throws Exception
	{
		return new EmbeddedSolrServerFactory("classpath:solr");
	}

	/**
	 * @param factory
	 *                the solr client factory
	 * @return the solr template
	 */
	@Bean
	public SolrTemplate solrTemplate(SolrClientFactory factory)
	{
		return new SolrTemplate(factory);
	}
}

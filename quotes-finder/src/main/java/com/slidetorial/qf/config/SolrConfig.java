/**
 *
 */
package com.slidetorial.qf.config;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.repository.config.EnableSolrRepositories;
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
	 * @param client
	 *                the solr client
	 * @return the solr template
	 */
	@Bean
	public SolrTemplate solrTemplate(SolrClient client)
	{
		return new SolrTemplate(client);
	}

	/**
	 * A config to provide {@link SolrClient} for production environment.
	 *
	 * @author goobar
	 *
	 */
	@Configuration
	@Profile({ "production" })
	public static class ProductionSolrClientConfig
	{
		/**
		 * @return the http solr client factory
		 * @throws Exception
		 *                 if fails
		 */
		@Bean
		public SolrClient solrClient() throws Exception
		{
			return new HttpSolrClient("http://localhost:8983/solr");
		}
	}

	/**
	 * A config to provide {@link SolrClient} for test purposes. This is
	 * also the default client used, when no other profile is activated.
	 *
	 * @author goobar
	 *
	 */
	@Configuration
	@Profile({ "test", "default" })
	public static class TestSolrClientConfig
	{

		/**
		 * @return the embedded solr client factory
		 * @throws Exception
		 *                 if fails
		 */
		@Bean
		public SolrClient solrClient() throws Exception
		{
			return new EmbeddedSolrServerFactory("classpath:solr")
				.getSolrClient();
		}

	}
}

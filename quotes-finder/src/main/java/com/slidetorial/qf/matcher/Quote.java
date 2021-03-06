/**
 *
 */
package com.slidetorial.qf.matcher;

import static com.google.common.base.Preconditions.checkNotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;

/**
 * A data class representing a quote.
 *
 * @author goobar
 *
 */
@SolrDocument(solrCoreName = "collection1")
public class Quote
{
	@Indexed(name = "author", type = "string")
	private final String author;

	@Id
	@Indexed(name = "id", type = "string", required = true)
	private final String id;

	@Indexed(name = "sentence", type = "textTight", stored = true)
	private final String sentence;

	/**
	 * @param id
	 *                unique identifier
	 * @param sentence
	 *                a sentence
	 * @param author
	 *                author of the quote
	 * @throws NullPointerException
	 *                 if any argument is null
	 */
	public Quote(String id, String sentence, String author)
		throws NullPointerException
	{
		validate(id, sentence, author);
		this.id = id;
		this.sentence = sentence;
		this.author = author;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
		{
			return true;
		}
		if (obj == null)
		{
			return false;
		}
		if (getClass() != obj.getClass())
		{
			return false;
		}
		Quote other = (Quote) obj;
		if (id == null)
		{
			if (other.id != null)
			{
				return false;
			}
		}
		else if (!id.equals(other.id))
		{
			return false;
		}
		return true;
	}

	/**
	 * @return the author
	 */
	public String getAuthor()
	{
		return author;
	}

	/**
	 * @return the id
	 */
	public String getId()
	{
		return id;
	}

	/**
	 * @return the sentence, might an empty string
	 */
	public String getSentence()
	{
		return sentence;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "Quote [sentence=" + sentence + "]";
	}

	/**
	 * @param id
	 * @param sentence
	 * @param author
	 */
	private void validate(String id, String sentence, String author)
	{
		checkNotNull(id, "'id' cannot be null");
		checkNotNull(sentence, "'sentence' cannot be null");
		checkNotNull(author, "'author' cannot be null");
	}
}

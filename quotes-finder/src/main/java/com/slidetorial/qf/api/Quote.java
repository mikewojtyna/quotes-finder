/**
 *
 */
package com.slidetorial.qf.api;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * A data class representing a quote.
 *
 * @author goobar
 *
 */
public class Quote
{
	private final String sentence;

	/**
	 * @param sentence
	 *                a sentence
	 * @throws NullPointerException
	 *                 if any argument is null
	 */
	public Quote(String sentence) throws NullPointerException
	{
		validate(sentence);
		this.sentence = sentence;
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
		if (sentence == null)
		{
			if (other.sentence != null)
			{
				return false;
			}
		}
		else if (!sentence.equals(other.sentence))
		{
			return false;
		}
		return true;
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
		result = prime * result
			+ ((sentence == null) ? 0 : sentence.hashCode());
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
	 * @param sentence
	 */
	private void validate(String sentence)
	{
		checkNotNull(sentence, "'sentence' cannot be null");
	}
}

/**
 *
 */
package com.slidetorial.qf.matcher;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Represents a "phrase". For the needs of this project, we define it as
 * <strong>any meaningful text which can be used to find similarities between
 * two strings</strong>.
 *
 * @author goobar
 * @implSpec currently it's just a simple string wrapper, this class is
 *           introduced mainly to give it a meaningful name, which can be used
 *           when discussing details of the project
 *
 */
public class Phrase
{

	private final String text;

	/**
	 * @param text
	 *                text
	 * @throws NullPointerException
	 *                 if any argument is null
	 */
	public Phrase(String text) throws NullPointerException
	{
		validate(text);
		this.text = text;
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
		Phrase other = (Phrase) obj;
		if (text == null)
		{
			if (other.text != null)
			{
				return false;
			}
		}
		else if (!text.equals(other.text))
		{
			return false;
		}
		return true;
	}

	/**
	 * @return the text
	 */
	public String getText()
	{
		return text;
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
			+ ((text == null) ? 0 : text.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "Phrase [text=" + text + "]";
	}

	/**
	 * @param text
	 */
	private void validate(String text)
	{
		checkNotNull(text, "'text' cannot be null");
	}

}

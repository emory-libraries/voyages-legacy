package edu.emory.library.tast.common.grideditor.textbox;

import edu.emory.library.tast.common.grideditor.Value;
import edu.emory.library.tast.util.StringUtils;

public class TextareaValue extends Value
{
	
	private String[] texts;
	private String[] rollovers;
	
	public TextareaValue(String[] texts)
	{
		
		int n = 0;
		for (int i = 0; i < texts.length; i++)
			if (!StringUtils.isNullOrEmpty(texts[i])) n++;
		
		int j = 0;
		this.texts = new String[n];
		for (int i = 0; i < texts.length; i++)
			if (!StringUtils.isNullOrEmpty(texts[i]))
				this.texts[j++] = texts[i];

	}

	public TextareaValue(String text)
	{
		this(new String[] {text});
	}

	public String getText()
	{
		StringBuffer buffer = new StringBuffer();
		if (texts != null) {
			for (int i = 0; i < texts.length; i++) {
				if (!StringUtils.isNullOrEmpty(texts[i])) {
					buffer.append(texts[i]).append("\n");
				}
			}
		}
		return buffer.toString();
	}

	public void setText(String[] texts)
	{
		this.texts = texts;
	}
	
	public boolean isEmptyOrNull()
	{
		return texts == null;
	}

	public String[] getTexts()
	{
		return texts;
	}

	public String toString()
	{
		return StringUtils.join("\n", texts);
	}

	public boolean equals(Object obj)
	{

		if (obj == this)
			return true;

		if (obj == null)
			return false;

		if (!(obj instanceof TextareaValue))
			return false;

		TextareaValue that = (TextareaValue) obj;

		return StringUtils.compareStringArrays(this.texts, that.texts, true);
		
	}
	
	public boolean isCorrectValue() {
		return true;
	}

	public boolean isEmpty() {
		return texts == null || texts.length == 0;
	}

	public String[] getRollovers() {
		return rollovers;
	}

	public void setRollovers(String[] rollovers) {
		this.rollovers = rollovers;
	}

}
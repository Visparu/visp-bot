package com.visparu.vispbot.exceptions.internal;

public class ConfigurationException extends Exception
{
	private static final long serialVersionUID = -3608941895121694968L;
	
	public ConfigurationException(Exception e)
	{
		super(e);
	}
}

package com.visparu.vispbot.exceptions.internal;

public class ConfigurationRuntimeException extends RuntimeException
{
	private static final long serialVersionUID = -295310529906710872L;
	
	public ConfigurationRuntimeException(Exception e)
	{
		super(e);
	}
}

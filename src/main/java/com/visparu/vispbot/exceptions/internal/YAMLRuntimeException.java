package com.visparu.vispbot.exceptions.internal;

public class YAMLRuntimeException extends RuntimeException
{
	private static final long serialVersionUID = 1476059516434353722L;
	
	public YAMLRuntimeException(Exception e)
	{
		super(e);
	}
}

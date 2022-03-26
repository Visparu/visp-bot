package com.visparu.vispbot.exceptions.internal;

public class JSONRuntimeException extends RuntimeException
{	
	private static final long serialVersionUID = -5871082713353843826L;

	public JSONRuntimeException(Exception e)
	{
		super(e);
	}
}

package com.visparu.vispbot.exceptions;

public class ExternalException extends Exception
{
	private static final long serialVersionUID = -1110988382951337357L;
	
	private final String externalMessage;
	
	public ExternalException(String externalMessage)
	{
		super(externalMessage);
		this.externalMessage = externalMessage;
	}
	
	public String getExternalMessage()
	{
		return this.externalMessage;
	}
}

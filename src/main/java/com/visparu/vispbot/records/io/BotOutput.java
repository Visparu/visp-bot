package com.visparu.vispbot.records.io;

public class BotOutput
{
	private final String payloadString;
	private final String errorString;
	
	public BotOutput(String payloadString, String errorString)
	{
		this.payloadString = payloadString;
		this.errorString   = errorString;
	}
	
	public String getPayloadString()
	{
		return payloadString;
	}
	
	public String getErrorString()
	{
		return errorString;
	}
}

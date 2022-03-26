package com.visparu.vispbot.exceptions.external;

import com.visparu.vispbot.exceptions.ExternalException;

public class InvalidCommandException extends ExternalException
{
	private static final long serialVersionUID = -8774804854811498210L;
	
	public InvalidCommandException()
	{
		super("Error: The command could not be parsed.");
	}
	
	public InvalidCommandException(String msg)
	{
		super(String.format("Error: The command could not be parsed. %s", msg));
	}
}

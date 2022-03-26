package com.visparu.vispbot.exceptions.external;

import com.visparu.vispbot.exceptions.ExternalException;

public class NoSuchCommandException extends ExternalException
{
	private static final long serialVersionUID = 4419171083277495691L;
	
	private final String commandName;
	
	public NoSuchCommandException(String commandName)
	{
		super(String.format("Error: Command '%s' not found!", commandName));
		this.commandName = commandName;
	}
	
	public String getCommandName()
	{
		return commandName;
	}
}

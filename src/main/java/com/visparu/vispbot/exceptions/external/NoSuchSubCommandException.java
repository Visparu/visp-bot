package com.visparu.vispbot.exceptions.external;

import com.visparu.vispbot.exceptions.ExternalException;

public class NoSuchSubCommandException extends ExternalException
{
	private static final long serialVersionUID = 4419171083277495691L;
	
	private final String commandName;
	
	public NoSuchSubCommandException(String commandName)
	{
		super(String.format("Error: Subcommand '%s' not found!", commandName));
		this.commandName = commandName;
	}
	
	public String getCommandName()
	{
		return commandName;
	}
}

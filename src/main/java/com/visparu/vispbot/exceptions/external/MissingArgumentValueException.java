package com.visparu.vispbot.exceptions.external;

import com.visparu.vispbot.commands.CommandArgument;
import com.visparu.vispbot.exceptions.ExternalException;

public class MissingArgumentValueException extends ExternalException
{
	private static final long serialVersionUID = 8567371662683128518L;
	
	// TODO: this should not be transient. Make CommandArgument serializable.
	private final transient CommandArgument commandArgument;
	
	public MissingArgumentValueException(CommandArgument commandArgument)
	{
		super(String.format("Argument '%s' expects a value but was given none. (Expected value type: '%s')", commandArgument.getLongName(), commandArgument.getValueName()));
		this.commandArgument = commandArgument;
	}
	
	public CommandArgument getCommandArgument()
	{
		return this.commandArgument;
	}
}

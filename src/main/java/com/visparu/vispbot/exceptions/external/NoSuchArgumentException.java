package com.visparu.vispbot.exceptions.external;

import com.visparu.vispbot.exceptions.ExternalException;

public class NoSuchArgumentException extends ExternalException
{
	private static final long serialVersionUID = -1364723584277662910L;
	
	private final String argument;
	
	public NoSuchArgumentException(String argument)
	{
		super(String.format("Error: No such argument '%s'", argument));
		this.argument = argument;
	}
	
	public NoSuchArgumentException(Character argument)
	{
		super(String.format("Error: No such argument: '%s'", argument));
		this.argument = argument.toString();
	}
	
	public String getArgument()
	{
		return argument;
	}
}

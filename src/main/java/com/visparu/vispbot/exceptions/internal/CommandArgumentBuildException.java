package com.visparu.vispbot.exceptions.internal;

import com.visparu.vispbot.commands.CommandArgument;

public class CommandArgumentBuildException extends RuntimeException
{
	private static final long serialVersionUID = 7249607550373232273L;
	
	//TODO: this is not supposed to be transient. Make the class serializable instead.
	private final transient CommandArgument instance;
	
	public CommandArgumentBuildException(CommandArgument instance, String msg)
	{
		super(msg);
		this.instance = instance;
	}
	
	public CommandArgument getInstance()
	{
		return this.instance;
	}
}

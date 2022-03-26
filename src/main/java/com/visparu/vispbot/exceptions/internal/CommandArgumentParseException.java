package com.visparu.vispbot.exceptions.internal;

public class CommandArgumentParseException extends RuntimeException
{
	private static final long serialVersionUID = 7249607550373232273L;
	
	private final String[] args;
	
	public CommandArgumentParseException(String[] args, String msg)
	{
		super(msg);
		this.args = args;
	}
	
	public String[] getArgs()
	{
		return this.args;
	}
}

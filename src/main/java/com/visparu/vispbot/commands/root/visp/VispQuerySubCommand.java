package com.visparu.vispbot.commands.root.visp;

import java.util.List;

import com.visparu.vispbot.commands.Command;
import com.visparu.vispbot.commands.CommandArgument;
import com.visparu.vispbot.records.io.BotOutput;

public class VispQuerySubCommand implements Command
{
	@Override
	public String getCommandName()
	{
		return "query";
	}
	
	@Override
	public String getShortCommandDescription()
	{
		return "Queries a question to the primary execution daemon";
	}
	
	@Override
	public String getLongCommandDescription()
	{
		return "Queries a question to the primary execution daemon. This is an asynchronous operation and will not resolve immediately.";
	}
	
	@Override
	public List<CommandArgument> getCommandArguments()
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public BotOutput execute(String[] args)
	{
		// TODO Auto-generated method stub
		return null;
	}
}

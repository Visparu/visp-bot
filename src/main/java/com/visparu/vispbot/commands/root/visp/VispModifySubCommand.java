package com.visparu.vispbot.commands.root.visp;

import java.util.List;

import com.visparu.vispbot.commands.Command;
import com.visparu.vispbot.commands.CommandArgument;
import com.visparu.vispbot.records.io.BotOutput;

public class VispModifySubCommand implements Command
{
	@Override
	public String getCommandName()
	{
		return "modify";
	}
	
	@Override
	public String getShortCommandDescription()
	{
		return "Modifies the execution environment of the primary execution daemon";
	}
	
	@Override
	public String getLongCommandDescription()
	{
		return "Modifies the execution environment of the primary execution daemon.\n"
			+ "This may result in an unstable state, use with caution!";
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

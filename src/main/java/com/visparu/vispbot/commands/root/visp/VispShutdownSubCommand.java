package com.visparu.vispbot.commands.root.visp;

import java.util.List;

import com.visparu.vispbot.commands.Command;
import com.visparu.vispbot.commands.CommandArgument;
import com.visparu.vispbot.records.io.BotOutput;

public class VispShutdownSubCommand implements Command
{
	@Override
	public String getCommandName()
	{
		return "shutdown";
	}
	
	@Override
	public String getShortCommandDescription()
	{
		return "Shuts down the primary execution daemon";
	}
	
	@Override
	public String getLongCommandDescription()
	{
		return "Shuts down the primary execution daemon. Only executable if the daemon is currently running.";
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

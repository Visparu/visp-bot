package com.visparu.vispbot.commands.root.visp;

import java.util.List;

import com.visparu.vispbot.commands.Command;
import com.visparu.vispbot.commands.CommandArgument;

public class VispStartSubCommand implements Command
{
	@Override
	public String getCommandName()
	{
		return "start";
	}
	
	@Override
	public String getShortCommandDescription()
	{
		return "Starts the primary execution daemon";
	}
	
	@Override
	public String getLongCommandDescription()
	{
		return "Starts the primary execution daemon. Only executable if the primary execution daemon is not already running.";
	}

	@Override
	public List<CommandArgument> getCommandArguments()
	{
		// TODO
		return List.of();
	}

	@Override
	public String execute(String[] args)
	{
		// TODO
		return null;
	}
}

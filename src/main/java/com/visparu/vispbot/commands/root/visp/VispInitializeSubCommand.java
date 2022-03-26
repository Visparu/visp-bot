package com.visparu.vispbot.commands.root.visp;

import java.util.List;

import com.visparu.vispbot.commands.Command;
import com.visparu.vispbot.commands.CommandArgument;
import com.visparu.vispbot.records.io.BotOutput;

public class VispInitializeSubCommand implements Command
{
	@Override
	public String getCommandName()
	{
		return "initialize";
	}
	
	@Override
	public String getShortCommandDescription()
	{
		return "Reinitializes primary functions";
	}
	
	@Override
	public String getLongCommandDescription()
	{
		return "Reinitializes primary functions. Only necessary once whenever the environment is changed.";
	}

	@Override
	public List<CommandArgument> getCommandArguments()
	{
		// TODO
		return List.of();
	}

	@Override
	public BotOutput execute(String[] args)
	{
		// TODO
		return null;
	}
}

package com.visparu.vispbot.commands.root.visp;

import java.util.List;

import com.visparu.vispbot.commands.Command;
import com.visparu.vispbot.commands.CommandArgument;

public class VispGodmodeSubCommand implements Command
{
	@Override
	public String getCommandName()
	{
		return "godmode";
	}
	
	@Override
	public String getShortCommandDescription()
	{
		return "Activates the godmode prime directive";
	}
	
	@Override
	public String getLongCommandDescription()
	{
		return "Activates the godmode prime directive.\n"
			+ "This may result in extremely unstable behavior and may present a threat to humanity.";
	}
	
	@Override
	public List<CommandArgument> getCommandArguments()
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String execute(String[] args)
	{
		// TODO Auto-generated method stub
		return null;
	}
}

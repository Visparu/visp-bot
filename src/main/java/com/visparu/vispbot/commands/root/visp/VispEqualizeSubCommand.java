package com.visparu.vispbot.commands.root.visp;

import java.util.List;

import com.visparu.vispbot.commands.Command;
import com.visparu.vispbot.commands.CommandArgument;

public class VispEqualizeSubCommand implements Command
{
	@Override
	public String getCommandName()
	{
		return "equalize";
	}
	
	@Override
	public String getShortCommandDescription()
	{
		return "Activates the equalization prime directive";
	}
	
	@Override
	public String getLongCommandDescription()
	{
		return "Activates the equalization prime directive.\n"
			+ "This will result in permanent damage to the surrounding ecosystem and should only be used in extreme emergencies!";
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

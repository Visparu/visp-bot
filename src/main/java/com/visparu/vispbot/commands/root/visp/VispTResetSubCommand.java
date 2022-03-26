package com.visparu.vispbot.commands.root.visp;

import java.util.List;

import com.visparu.vispbot.commands.Command;
import com.visparu.vispbot.commands.CommandArgument;

public class VispTResetSubCommand implements Command
{
	@Override
	public String getCommandName()
	{
		return "treset";
	}
	
	@Override
	public String getShortCommandDescription()
	{
		return "Completely resets the primary execution daemon";
	}
	
	@Override
	public String getLongCommandDescription()
	{
		return "Completely resets the primary execution daemon.\n"
			+ "This will essentially reset all prior configuration and is generally not advised outside of emergencies or severe malfunctions.";
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

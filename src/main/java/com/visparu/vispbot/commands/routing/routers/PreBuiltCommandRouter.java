package com.visparu.vispbot.commands.routing.routers;

import java.util.Map;
import java.util.TreeMap;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.visparu.vispbot.commands.Command;
import com.visparu.vispbot.commands.root.VispCommand;
import com.visparu.vispbot.commands.routing.CommandRouter;
import com.visparu.vispbot.exceptions.ExternalException;
import com.visparu.vispbot.exceptions.external.InvalidCommandException;
import com.visparu.vispbot.exceptions.external.NoSuchCommandException;

public class PreBuiltCommandRouter implements CommandRouter
{
	private Map<String, Command> commandMap = new TreeMap<>();
	
	@Autowired
	private VispCommand vispCommand;
	
	@PostConstruct
	private void initialize()
	{
		this.commandMap.put("visp", this.vispCommand);
	}
	
	@Override
	public Map<String, Command> getCommandMap()
	{
		return Map.copyOf(this.commandMap);
	}
	
	@Override
	public Command findCommandByName(String commandName) throws NoSuchCommandException
	{
		Command command = this.commandMap.get(commandName);
		if (command == null)
		{
			throw new NoSuchCommandException(commandName);
		}
		return command;
	}
	
	@Override
	public String route(String[] args) throws ExternalException
	{
		if (args.length < 1)
		{
			throw new InvalidCommandException();
		}
		String commandName = args[0];
		return this.findCommandByName(commandName).execute(args);
	}
}

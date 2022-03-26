package com.visparu.vispbot.commands.routing;

import java.util.Map;

import com.visparu.vispbot.commands.Command;
import com.visparu.vispbot.exceptions.ExternalException;
import com.visparu.vispbot.exceptions.external.NoSuchCommandException;

public interface CommandRouter
{
	Map<String, Command> getCommandMap();
	
	Command findCommandByName(String commandName) throws NoSuchCommandException;
	
	String route(String[] args) throws ExternalException;
}

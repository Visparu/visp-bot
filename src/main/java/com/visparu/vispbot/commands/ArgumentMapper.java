package com.visparu.vispbot.commands;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.visparu.vispbot.exceptions.ExternalException;
import com.visparu.vispbot.exceptions.external.MissingArgumentValueException;
import com.visparu.vispbot.exceptions.external.NoSuchArgumentException;

public class ArgumentMapper
{
	private Map<CommandArgument, String> argumentMap           = new HashMap<>();
	private List<String>                 unnamedArgumentValues = new ArrayList<>();
	private List<CommandArgument>        commandArguments;
	private CommandArgument              cachedCommandArgument;
	
	public ArgumentMapper(List<CommandArgument> commandArguments)
	{
		this.commandArguments      = commandArguments;
		this.cachedCommandArgument = null;
	}
	
	public Map<CommandArgument, String> getArgumentMap()
	{
		return this.argumentMap;
	}
	
	public String getArgumentValue(String argumentLongName)
	{
		Entry<CommandArgument, String> entry = this.argumentMap.entrySet().stream()
			.filter(e -> e.getKey().getLongName().equals(argumentLongName))
			.findFirst()
			.orElse(null);
		
		if(entry == null)
		{
			return null;
		}
		return entry.getValue();
	}
	
	public List<String> getUnnamedArgumentValues()
	{
		return this.unnamedArgumentValues;
	}
	
	public void mapArguments(String[] args) throws ExternalException
	{
		for (String arg : args)
		{
			if (this.cachedCommandArgument != null)
			{
				this.processArgumentValue(arg);
			}
			else
			{
				this.processArgument(arg);
			}
		}
		if (this.cachedCommandArgument != null)
		{
			throw new MissingArgumentValueException(this.cachedCommandArgument);
		}
	}
	
	private void processArgumentValue(String arg)
	{
		argumentMap.put(this.cachedCommandArgument, arg);
		this.cachedCommandArgument = null;
	}
	
	private void processArgument(String arg) throws NoSuchArgumentException, MissingArgumentValueException
	{
		if (arg.startsWith("--"))
		{
			this.processArgumentLongForm(arg);
		}
		else if (arg.startsWith("-"))
		{
			this.processArgumentShortForm(arg);
		}
		else
		{
			this.unnamedArgumentValues.add(arg);
		}
	}
	
	private void processArgumentLongForm(String arg) throws NoSuchArgumentException
	{
		String          argumentLongName        = arg.substring(2);
		CommandArgument matchingCommandArgument = this.commandArguments.stream()
			.filter(commandArgument -> commandArgument.hasName() && commandArgument.getLongName().equals(argumentLongName))
			.findFirst()
			.orElseThrow(() -> new NoSuchArgumentException(argumentLongName));
		if (matchingCommandArgument.hasValue())
		{
			this.cachedCommandArgument = matchingCommandArgument;
		}
		else
		{
			this.argumentMap.put(matchingCommandArgument, "true");
		}
	}
	
	private void processArgumentShortForm(String arg) throws NoSuchArgumentException, MissingArgumentValueException
	{
		String argumentShortNameSequence = arg.substring(1);
		for (int i = 0; i < argumentShortNameSequence.length(); i++)
		{
			char            argumentShortName       = argumentShortNameSequence.charAt(i);
			CommandArgument matchingCommandArgument = this.commandArguments.stream()
				.filter(commandArgument -> commandArgument.hasName() && commandArgument.getShortName().equals(argumentShortName))
				.findFirst()
				.orElseThrow(() -> new NoSuchArgumentException(argumentShortName));
			if (matchingCommandArgument.hasNoValue())
			{
				this.argumentMap.put(matchingCommandArgument, "true");
			}
			else
			{
				this.processArgumentShortFormWithValue(i, argumentShortNameSequence.length(), matchingCommandArgument);
			}
		}
	}
	
	private void processArgumentShortFormWithValue(int i, int length, CommandArgument commandArgument) throws MissingArgumentValueException
	{
		if (i == length - 1)
		{
			this.cachedCommandArgument = commandArgument;
		}
		else
		{
			throw new MissingArgumentValueException(commandArgument);
		}
	}
}

package com.visparu.vispbot.commands;

import java.util.List;
import java.util.StringJoiner;

import com.visparu.vispbot.exceptions.ExternalException;
import com.visparu.vispbot.exceptions.external.NoSuchArgumentException;
import com.visparu.vispbot.records.io.BotOutput;

public interface Command
{
	String getCommandName();
	
	String getShortCommandDescription();
	
	String getLongCommandDescription();
	
	List<CommandArgument> getCommandArguments();
	
	BotOutput execute(String[] args) throws ExternalException;
	
	default String createCommandHelp()
	{
		StringJoiner sj = new StringJoiner("\n");
		sj.add(String.format("Help menu for command '%s'.", this.getCommandName()));
		sj.add("");
		sj.add(this.createCommandHelpUsage());
		sj.add("");
		sj.add(this.createArgumentDescriptions());
		
		return sj.toString();
	}
	
	default String createCommandHelpUsage()
	{
		StringBuilder sb = new StringBuilder();
		
		sb.append("Usage: ");
		
		sb.append(this.getCommandName());
		sb.append(" ");
		
		List<CommandArgument> namelessArgs = this.getCommandArguments().stream()
			.filter(CommandArgument::hasNoName)
			.toList();
		namelessArgs.forEach(arg ->
		{
			sb.append(arg.isMandatory() ? "<" : "[");
			sb.append(arg.getValueName());
			sb.append(arg.isMandatory() ? ">" : "]");
			sb.append(" ");
		});
		
		List<CommandArgument> valuelessArgs = this.getCommandArguments().stream()
			.filter(CommandArgument::hasNoValue)
			.toList();
		if (!valuelessArgs.isEmpty())
		{
			sb.append("[");
			sb.append("-");
			valuelessArgs.forEach(arg -> sb.append(arg.getShortName()));
			sb.append("]");
			sb.append(" ");
		}
		
		List<CommandArgument> valueArgs = this.getCommandArguments().stream()
			.filter(CommandArgument::hasName)
			.filter(CommandArgument::hasValue)
			.toList();
		valueArgs.forEach(arg ->
		{
			sb.append(arg.isMandatory() ? "<" : "[");
			sb.append("-");
			sb.append(arg.getShortName());
			sb.append(" ");
			sb.append("<");
			sb.append(arg.getValueName());
			sb.append(">");
			sb.append(arg.isMandatory() ? ">" : "]");
		});
		
		return sb.toString();
	}
	
	default String createArgumentDescriptions()
	{
		StringJoiner argumentSJ = new StringJoiner("\n");
		argumentSJ.add("Argument descriptions:");
		argumentSJ.add("");
		this.getCommandArguments().forEach(arg ->
		{
			if (arg.hasNoName())
			{
				StringBuilder unnamedArgSB = new StringBuilder();
				unnamedArgSB.append("<");
				unnamedArgSB.append(arg.getValueName());
				unnamedArgSB.append(">");
				argumentSJ.add(unnamedArgSB);
			}
			else
			{
				StringBuilder namedArgSB = new StringBuilder();
				namedArgSB.append("-");
				namedArgSB.append(arg.getShortName());
				namedArgSB.append("|");
				namedArgSB.append("--");
				namedArgSB.append(arg.getLongName());
				namedArgSB.append(" ");
				if (arg.hasValue())
				{
					namedArgSB.append("<");
					namedArgSB.append(arg.getValueName());
					namedArgSB.append(">");
					namedArgSB.append(" ");
				}
				argumentSJ.add(namedArgSB);
			}
			StringBuilder descriptionSB = new StringBuilder();
			descriptionSB.append("  ");
			descriptionSB.append(arg.getDescription());
			argumentSJ.add(descriptionSB);
			argumentSJ.add("");
		});
		return argumentSJ.toString();
	}
	
	default CommandArgument getCommandArgument(Character shortForm) throws NoSuchArgumentException
	{
		return this.getCommandArguments().stream()
			.filter(arg -> shortForm.equals(arg.getShortName()))
			.findAny()
			.orElseThrow(() -> new NoSuchArgumentException(shortForm.toString()));
	}
}

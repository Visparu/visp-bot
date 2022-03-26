package com.visparu.vispbot.commands.root;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

import javax.annotation.PostConstruct;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.visparu.vispbot.commands.ArgumentMapper;
import com.visparu.vispbot.commands.Command;
import com.visparu.vispbot.commands.CommandArgument;
import com.visparu.vispbot.commands.root.visp.VispEqualizeSubCommand;
import com.visparu.vispbot.commands.root.visp.VispGodmodeSubCommand;
import com.visparu.vispbot.commands.root.visp.VispInitializeSubCommand;
import com.visparu.vispbot.commands.root.visp.VispModifySubCommand;
import com.visparu.vispbot.commands.root.visp.VispQuerySubCommand;
import com.visparu.vispbot.commands.root.visp.VispShutdownSubCommand;
import com.visparu.vispbot.commands.root.visp.VispStartSubCommand;
import com.visparu.vispbot.commands.root.visp.VispTResetSubCommand;
import com.visparu.vispbot.exceptions.ExternalException;
import com.visparu.vispbot.exceptions.external.NoSuchSubCommandException;

@Component
public class VispCommand implements Command
{
	@Autowired
	private List<Command>         subCommands;
	private List<CommandArgument> commandArguments;
	
	@PostConstruct
	public void initialize()
	{
		this.commandArguments = new ArrayList<>();
		this.commandArguments.add(CommandArgument.builder()
			.withValueName("sub command")
			.withDescription(this.createSubCommandDescription())
			.withMandatory(true)
			.build());
		this.commandArguments.add(CommandArgument.builder()
			.withShortName('h')
			.withLongName("help")
			.withDescription("Display this help menu, or the help menu for a specific sub command.")
			.withMandatory(false)
			.build());
		this.commandArguments.add(CommandArgument.builder()
			.withShortName('t')
			.withLongName("test")
			.withDescription("Test argument.")
			.withMandatory(false)
			.withValueName("test value")
			.build());
	}
	
	private String createSubCommandDescription()
	{
		int longestNameLength = this.subCommands.stream()
			.sorted(this::compareSubCommandNameLength)
			.findFirst()
			.orElseThrow()
			.getCommandName()
			.length();
		
		StringJoiner sj = new StringJoiner("\n");
		sj.add("Available sub commands:");
		this.subCommands.forEach(subCommand -> sj.add(String.format("  - %-" + longestNameLength + "s (%s)", subCommand.getCommandName(), subCommand.getShortCommandDescription())));
		sj.add("  For more information on a specific sub command, please use 'visp <sub command> -h'");
		return sj.toString();
	}
	
	private int compareSubCommandNameLength(Command c1, Command c2)
	{
		return c2.getCommandName().length() - c1.getCommandName().length();
	}
	
	@Override
	public String getCommandName()
	{
		return "visp";
	}
	
	@Override
	public String getShortCommandDescription()
	{
		return "The visp prime executable";
	}
	
	@Override
	public String getLongCommandDescription()
	{
		return "This is the prime control executable for the VispCore MK XXIV AI module. For use of trained personnel only!";
	}
	
	@Override
	public List<CommandArgument> getCommandArguments()
	{
		return List.copyOf(this.commandArguments);
	}
	
	@Override
	public String execute(String[] args) throws ExternalException
	{
		ArgumentMapper argumentMapper = new ArgumentMapper(this.commandArguments);
		argumentMapper.mapArguments(args);
		
		String targetSubCommand = argumentMapper.getUnnamedArgumentValues().get(0);
		
		Command matchingSubCommand = subCommands.stream()
			.filter(subCommand -> subCommand.getCommandName().equals(targetSubCommand))
			.findFirst()
			.orElseThrow(() -> new NoSuchSubCommandException(targetSubCommand));
		
		return matchingSubCommand.execute(Arrays.copyOfRange(args, 1, args.length));
	}
	
	@Test
	public void test() throws ExternalException
	{
		List<Command> tempVispSubCommands = new ArrayList<>();
		tempVispSubCommands.add(new VispStartSubCommand());
		tempVispSubCommands.add(new VispInitializeSubCommand());
		tempVispSubCommands.add(new VispModifySubCommand());
		tempVispSubCommands.add(new VispGodmodeSubCommand());
		tempVispSubCommands.add(new VispEqualizeSubCommand());
		tempVispSubCommands.add(new VispQuerySubCommand());
		tempVispSubCommands.add(new VispTResetSubCommand());
		tempVispSubCommands.add(new VispShutdownSubCommand());
		this.subCommands = List.copyOf(tempVispSubCommands);
		
		this.initialize();
		
		System.out.println(this.createCommandHelp());
		
		ArgumentMapper argumentMapper = new ArgumentMapper(this.commandArguments);
		argumentMapper.mapArguments("visp --test lol start -h".split(" "));
		System.out.println(argumentMapper.getArgumentMap());
		System.out.println(argumentMapper.getUnnamedArgumentValues());
	}
}

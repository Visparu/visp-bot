package com.visparu.vispbot;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.visparu.vispbot.commands.Command;
import com.visparu.vispbot.commands.root.VispCommand;
import com.visparu.vispbot.commands.root.visp.VispEqualizeSubCommand;
import com.visparu.vispbot.commands.root.visp.VispGodmodeSubCommand;
import com.visparu.vispbot.commands.root.visp.VispInitializeSubCommand;
import com.visparu.vispbot.commands.root.visp.VispModifySubCommand;
import com.visparu.vispbot.commands.root.visp.VispQuerySubCommand;
import com.visparu.vispbot.commands.root.visp.VispShutdownSubCommand;
import com.visparu.vispbot.commands.root.visp.VispStartSubCommand;
import com.visparu.vispbot.commands.root.visp.VispTResetSubCommand;

@Configuration
public class CommandSpringConfiguration
{
	private VispCommand   vispCommand;
	private List<Command> vispSubCommands;
	
	public CommandSpringConfiguration()
	{
		this.vispCommand = new VispCommand();
		
		List<Command> tempVispSubCommands = new ArrayList<>();
		tempVispSubCommands.add(new VispStartSubCommand());
		tempVispSubCommands.add(new VispInitializeSubCommand());
		tempVispSubCommands.add(new VispModifySubCommand());
		tempVispSubCommands.add(new VispGodmodeSubCommand());
		tempVispSubCommands.add(new VispEqualizeSubCommand());
		tempVispSubCommands.add(new VispQuerySubCommand());
		tempVispSubCommands.add(new VispTResetSubCommand());
		tempVispSubCommands.add(new VispShutdownSubCommand());
		
		this.vispSubCommands = List.copyOf(tempVispSubCommands);
	}
	
	@Bean
	public VispCommand vispCommand()
	{
		return vispCommand;
	}
	
	@Bean
	@Qualifier("VispSubCommands")
	public List<Command> vispSubCommands()
	{
		return this.vispSubCommands;
	}
}

package com.visparu.vispbot;

import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;

import com.visparu.vispbot.commands.routing.CommandRouter;
import com.visparu.vispbot.commands.routing.routers.PreBuiltCommandRouter;
import com.visparu.vispbot.config.ConfigurationLoader;
import com.visparu.vispbot.config.YAMLFileConfigurationLoader;
import com.visparu.vispbot.listeners.DefaultMessageListener;

import net.dv8tion.jda.api.hooks.EventListener;

@Configuration
public class RootSpringConfiguration
{
	private ApplicationListener<ContextRefreshedEvent> jdaLoadStartupListener;
	private ConfigurationLoader                        configurationLoader;
	private EventListener                              messageListener;
	private CommandRouter                              commandRouter;
	
	public RootSpringConfiguration()
	{
		this.jdaLoadStartupListener = new JDALoadStartupListener();
		
		this.configurationLoader = new YAMLFileConfigurationLoader("config.yml");
		
		this.messageListener = new DefaultMessageListener();
		
		this.commandRouter = new PreBuiltCommandRouter();
	}
	
	@Bean
	public ApplicationListener<ContextRefreshedEvent> jdaLoadStartupListener()
	{
		return this.jdaLoadStartupListener;
	}
	
	@Bean
	public ConfigurationLoader configurationLoader()
	{
		return this.configurationLoader;
	}
	
	@Bean
	public EventListener messageListener()
	{
		return this.messageListener;
	}
	
	@Bean
	public CommandRouter commandRouter()
	{
		return this.commandRouter;
	}
}

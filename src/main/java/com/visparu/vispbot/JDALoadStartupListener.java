package com.visparu.vispbot;

import javax.security.auth.login.LoginException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import com.visparu.vispbot.config.ConfigurationLoader;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.hooks.EventListener;

public class JDALoadStartupListener implements ApplicationListener<ContextRefreshedEvent>
{
	private static final Logger logger = LoggerFactory.getLogger(JDALoadStartupListener.class);
	
	@Autowired
	private ConfigurationLoader configurationLoader;
	
	@Autowired
	private EventListener messageListener;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event)
	{
		logger.info("Context initialization finished. Starting JDA initialization...");
		try
		{
			JDA jda = JDABuilder.createDefault(configurationLoader.get().getBotToken())
				.addEventListeners(this.messageListener)
				.build();
			jda.awaitReady();
			logger.info("Finished initializing JDA.");
		}
		catch (LoginException e)
		{
			logger.error("Error while logging in: ", e);
		}
		catch (InterruptedException e)
		{
			Thread.currentThread().interrupt();
			logger.error("Readiness check interrupted: ", e);
		}
	}
}

package com.visparu.vispbot.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.visparu.vispbot.commands.routing.CommandRouter;
import com.visparu.vispbot.config.ConfigurationLoader;
import com.visparu.vispbot.exceptions.ExternalException;

import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

@Controller
public class DefaultMessageListener extends ListenerAdapter
{
	private static final Logger logger = LoggerFactory.getLogger(DefaultMessageListener.class);
	
	@Autowired
	private ConfigurationLoader configurationLoader;
	
	@Autowired
	private CommandRouter commandRouter;
	
	@Override
	public void onMessageReceived(MessageReceivedEvent event)
	{
		logger.trace("Message received: '{}' - '{}'", event.getAuthor().getName(), event.getMessage().getContentRaw());
		if (event.isFromGuild() && event.isFromType(ChannelType.TEXT) && event.getChannel().getIdLong() == this.configurationLoader.get().getChannelId())
		{
			this.onMessageInConfiguredChannel(event);
		}
	}
	
	private void onMessageInConfiguredChannel(MessageReceivedEvent event)
	{
		logger.debug("Message sent by '{}': '{}'", event.getAuthor().getName(), event.getMessage().getContentRaw());
		if (event.getMember().getIdLong() != this.configurationLoader.get().getBotUserId())
		{
			this.onMessageByDifferentUser(event);
		}
	}
	
	private void onMessageByDifferentUser(MessageReceivedEvent event)
	{
		if (event.getMessage().getContentRaw().startsWith(this.configurationLoader.get().getCommandPrefix()))
		{
			this.onCommand(event);
		}
	}
	
	private void onCommand(MessageReceivedEvent event)
	{
		logger.info("Command identified: '{}' - '{}'", event.getAuthor().getName(), event.getMessage().getContentRaw());
		String   strippedCommand = event.getMessage().getContentRaw().substring(this.configurationLoader.get().getCommandPrefix().length());
		String[] commandArgs     = strippedCommand.split(" ");
		try
		{
			String rawResponse     = this.commandRouter.route(commandArgs);
			String responseContent = this.buildResponseContent(rawResponse);
			event.getMessage()
				.reply(responseContent)
				.complete();
		}
		catch (ExternalException e)
		{
			String responseContent = this.buildResponseContent(e.getExternalMessage());
			event.getMessage()
				.reply(responseContent)
				.complete();
		}
	}
	
	private String buildResponseContent(String message)
	{
		return String.format("```%n%s%n```", message);
	}
}

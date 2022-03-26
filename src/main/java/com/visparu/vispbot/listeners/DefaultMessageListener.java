package com.visparu.vispbot.listeners;

import java.util.StringJoiner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.visparu.vispbot.commands.routing.CommandRouter;
import com.visparu.vispbot.config.ConfigurationLoader;
import com.visparu.vispbot.exceptions.ExternalException;
import com.visparu.vispbot.records.io.BotOutput;

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
			BotOutput rawResponse     = this.commandRouter.route(commandArgs);
			String    responseContent = this.buildResponseContent(rawResponse);
			event.getMessage()
				.reply(responseContent)
				.complete();
		}
		catch (ExternalException e)
		{
			BotOutput responseData = new BotOutput(null, e.getExternalMessage());
			String rawResponse = this.buildResponseContent(responseData);
			event.getMessage()
				.reply(rawResponse)
				.complete();
		}
	}
	
	private String buildResponseContent(BotOutput botOutput)
	{
		StringJoiner sj = new StringJoiner("\n");
		if (botOutput == null)
		{
			return String.format("```%nError: This module is not implemented yet!%n```");
		}
		if (botOutput.getErrorString() != null)
		{
			String rawErrorOutput = String.format("```%n%s%n```", botOutput.getErrorString());
			sj.add(rawErrorOutput);
		}
		if (botOutput.getPayloadString() != null)
		{
			String rawPayloadOutput = String.format("```%n%s%n```", botOutput.getPayloadString());
			sj.add(rawPayloadOutput);
		}
		return sj.toString();
	}
}

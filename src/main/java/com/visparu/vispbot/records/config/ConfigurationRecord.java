package com.visparu.vispbot.records.config;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.visparu.vispbot.records.VBRecord;

@JsonInclude(Include.NON_NULL)
@JsonDeserialize(builder = ConfigurationRecord.ConfigurationRecordBuilder.class)
public class ConfigurationRecord implements VBRecord
{
	private String botToken;
	private Long   botUserId;
	private Long   channelId;
	private String commandPrefix;
	
	private ConfigurationRecord()
	{
		
	}
	
	@JsonGetter("BotToken")
	public String getBotToken()
	{
		return botToken;
	}
	
	@JsonGetter("BotUserId")
	public Long getBotUserId()
	{
		return this.botUserId;
	}
	
	@JsonGetter("ChannelId")
	public Long getChannelId()
	{
		return this.channelId;
	}
	
	@JsonGetter("CommandPrefix")
	public String getCommandPrefix()
	{
		return this.commandPrefix;
	}
	
	@JsonPOJOBuilder(buildMethodName = "build", withPrefix = "with")
	public static class ConfigurationRecordBuilder
	{
		private ConfigurationRecord instance;
		
		private ConfigurationRecordBuilder()
		{
			this.instance = new ConfigurationRecord();
		}
		
		@JsonSetter("BotToken")
		public ConfigurationRecordBuilder withBotToken(String botToken)
		{
			this.instance.botToken = botToken;
			return this;
		}
		
		@JsonSetter("BotUserId")
		public ConfigurationRecordBuilder withBotUserId(Long botUserId)
		{
			this.instance.botUserId = botUserId;
			return this;
		}
		
		@JsonSetter("ChannelId")
		public ConfigurationRecordBuilder withChannelId(Long channelId)
		{
			this.instance.channelId = channelId;
			return this;
		}
		
		@JsonSetter("CommandPrefix")
		public ConfigurationRecordBuilder withCommandPrefix(String commandPrefix)
		{
			this.instance.commandPrefix = commandPrefix;
			return this;
		}
		
		public ConfigurationRecord build()
		{
			return this.instance;
		}
	}
}

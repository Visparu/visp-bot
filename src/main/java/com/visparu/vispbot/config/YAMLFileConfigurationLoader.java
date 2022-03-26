package com.visparu.vispbot.config;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.visparu.vispbot.exceptions.internal.ConfigurationException;
import com.visparu.vispbot.exceptions.internal.ConfigurationRuntimeException;
import com.visparu.vispbot.records.VBRecord;
import com.visparu.vispbot.records.config.ConfigurationRecord;

public class YAMLFileConfigurationLoader implements ConfigurationLoader
{
	private String              configPath;
	private ConfigurationRecord cachedConfiguration;
	
	public YAMLFileConfigurationLoader(String configPath)
	{
		this.configPath = configPath;
	}
	
	private void load() throws ConfigurationException
	{
		try
		{
			String              configString = Files.readString(Paths.get(this.configPath));
			ConfigurationRecord config       = VBRecord.fromYAMLString(configString, ConfigurationRecord.class);
			
			this.cachedConfiguration = config;
		}
		catch (IOException e)
		{
			throw new ConfigurationException(e);
		}
	}
	
	public ConfigurationRecord get()
	{
		if (this.cachedConfiguration == null)
		{
			try
			{
				this.load();
			}
			catch (ConfigurationException e)
			{
				throw new ConfigurationRuntimeException(e);
			}
		}
		return this.cachedConfiguration;
	}
}

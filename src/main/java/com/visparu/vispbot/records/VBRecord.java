package com.visparu.vispbot.records;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.visparu.vispbot.exceptions.internal.JSONRuntimeException;
import com.visparu.vispbot.exceptions.internal.YAMLRuntimeException;

public interface VBRecord
{
	static <T extends VBRecord> T fromYAMLString(String yamlString, Class<T> targetClass)
	{
		try
		{
			return new ObjectMapper(new YAMLFactory()).readValue(yamlString, targetClass);
		}
		catch (JsonProcessingException e)
		{
			throw new YAMLRuntimeException(e);
		}
	}
	
	default String toYAMLString(boolean pretty)
	{
		try
		{
			if (pretty)
			{
				return new ObjectMapper(new YAMLFactory()).writerWithDefaultPrettyPrinter().writeValueAsString(this);
			}
			else
			{
				return new ObjectMapper(new YAMLFactory()).writeValueAsString(this);
			}
		}
		catch (JsonProcessingException e)
		{
			throw new YAMLRuntimeException(e);
		}
	}
	
	static <T extends VBRecord> T fromJSONString(String jsonString, Class<T> targetClass)
	{
		try
		{
			return new ObjectMapper().readValue(jsonString, targetClass);
		}
		catch (JsonProcessingException e)
		{
			throw new JSONRuntimeException(e);
		}
	}
	
	default String toJSONString(boolean pretty)
	{
		try
		{
			if (pretty)
			{
				return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(this);
			}
			else
			{
				return new ObjectMapper().writeValueAsString(this);
			}
		}
		catch (JsonProcessingException e)
		{
			throw new JSONRuntimeException(e);
		}
	}

}

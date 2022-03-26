package com.visparu.vispbot.commands;

import com.visparu.vispbot.exceptions.internal.CommandArgumentBuildException;

public class CommandArgument
{
	private Character shortName   = null;
	private String    longName    = null;
	private String    description = "";
	private String    valueName   = null;
	private boolean   mandatory   = false;
	
	private CommandArgument()
	{
		
	}
	
	public Character getShortName()
	{
		return shortName;
	}
	
	public String getLongName()
	{
		return longName;
	}
	
	public String getDescription()
	{
		return description;
	}
	
	public String getValueName()
	{
		return valueName;
	}
	
	public boolean isMandatory()
	{
		return mandatory;
	}
	
	public boolean hasValue()
	{
		return this.getValueName() != null;
	}
	
	public boolean hasNoValue()
	{
		return !this.hasValue();
	}
	
	public boolean hasName()
	{
		return this.getShortName() != null || this.getLongName() != null;
	}
	
	public boolean hasNoName()
	{
		return !this.hasName();
	}
	
	@Override
	public String toString()
	{
		if (this.hasName())
		{
			return String.format("<%s/%s>", this.shortName, this.longName);
		}
		else
		{
			return String.format("<%s>", this.valueName);
		}
	}
	
	@Override
	public boolean equals(Object o)
	{
		if (o == null)
		{
			return false;
		}
		if (!(o instanceof CommandArgument))
		{
			return false;
		}
		CommandArgument ca = (CommandArgument) o;
		if (ca.hasName())
		{
			if (this.hasNoName())
			{
				return false;
			}
			else
			{
				return this.getShortName().equals(ca.getShortName());
			}
		}
		else
		{
			if (this.hasName() || this.hasNoValue())
			{
				return false;
			}
			else
			{
				return this.getValueName().equals(ca.getValueName());
			}
		}
	}
	
	@Override
	public int hashCode()
	{
		if (this.hasName())
		{
			return this.longName.hashCode();
		}
		else
		{
			return this.valueName.hashCode() * 2;
		}
	}
	
	public static CommandArgumentBuilder builder()
	{
		return new CommandArgumentBuilder();
	}
	
	public static class CommandArgumentBuilder
	{
		private CommandArgument instance;
		
		private CommandArgumentBuilder()
		{
			this.instance = new CommandArgument();
		}
		
		public CommandArgumentBuilder withShortName(Character shortForm)
		{
			this.instance.shortName = shortForm;
			return this;
		}
		
		public CommandArgumentBuilder withLongName(String longForm)
		{
			this.instance.longName = longForm;
			return this;
		}
		
		public CommandArgumentBuilder withDescription(String description)
		{
			this.instance.description = description;
			return this;
		}
		
		public CommandArgumentBuilder withValueName(String valueName)
		{
			this.instance.valueName = valueName;
			return this;
		}
		
		public CommandArgumentBuilder withMandatory(boolean mandatory)
		{
			this.instance.mandatory = mandatory;
			return this;
		}
		
		public CommandArgument build()
		{
			if (!this.instance.hasName() && !this.instance.isMandatory())
			{
				throw new CommandArgumentBuildException(this.instance, "Nameless arguments must be mandatory.");
			}
			if (!this.instance.hasName() && !this.instance.hasValue())
			{
				throw new CommandArgumentBuildException(this.instance, "Nameless arguments must have a value name.");
			}
			if (this.instance.hasName() && this.instance.isMandatory())
			{
				throw new CommandArgumentBuildException(this.instance, "Named arguments must not be mandatory.");
			}
			if (this.instance.hasName() && this.instance.getShortName() == null)
			{
				throw new CommandArgumentBuildException(this.instance, "Named arguments must have short names.");
			}
			if (this.instance.hasName() && this.instance.getLongName() == null)
			{
				throw new CommandArgumentBuildException(this.instance, "Named arguments must have long names.");
			}
			return this.instance;
		}
	}
}

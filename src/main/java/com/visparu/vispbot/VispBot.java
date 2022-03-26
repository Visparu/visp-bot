package com.visparu.vispbot;

import org.apache.logging.log4j.core.config.Configurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class VispBot implements CommandLineRunner
{
	private static final Logger logger = LoggerFactory.getLogger(VispBot.class);
	
	public static void main(String[] args)
	{
		logger.info("Starting application...");
		new SpringApplicationBuilder(VispBot.class)
			.bannerMode(Banner.Mode.OFF)
			.run(args);
		logger.info("Application started successfully.");
	}

	@Override
	public void run(String... param)
	{
		Configurator.initialize(null, "log4j2.xml");
	}
}

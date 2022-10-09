package com.frameworks.selenium.config;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.ConfigFactory;
import org.aeonbits.owner.Mutable;

public class BaseConfig {

	/* The instance of BaseConfigSpec */
	private static final BaseConfigSpec INSTANCE = ConfigFactory.create(BaseConfigSpec.class);

	public static BaseConfigSpec getInstance() {
		return INSTANCE;
	}

	private BaseConfig() {

	}

	@Config.Sources({ "file:src/test/resources/config/BaseConfig.properties" })
	public interface BaseConfigSpec extends Mutable {

		@Config.Key("app.url")
		String getAppURL();

		@Key("app.driver.browser")
		@DefaultValue("CHROME")
		String getBrowser();

		@Key("app.driver.waitSeconds")
		long getWaitSeconds();
	}
}
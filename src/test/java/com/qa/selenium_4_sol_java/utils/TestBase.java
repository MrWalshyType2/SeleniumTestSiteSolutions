package com.qa.selenium_4_sol_java.utils;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public abstract class TestBase {

	protected WebDriver driver;
	private Class<? extends WebDriver> driverClass;
	
	public TestBase(Class<? extends WebDriver> driverClass) {
		this.driverClass = driverClass;
		if (driverClass == null) throw new IllegalArgumentException("Driver class must not be null");
	}

	@BeforeEach
	protected void setupTest() {
		driver = WebDriverManager.getInstance(driverClass)
								 .create();
	}
	
	@AfterEach
	protected void teardownTest() {
		driver.quit();
	}
}

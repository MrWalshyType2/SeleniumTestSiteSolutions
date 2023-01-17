package com.qa.selenium_4_sol_java.waits.exercise1;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.selenium_4_sol_java.utils.ExerciseURL;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WaitingForElements {

	private WebDriver driver;

	@BeforeAll
	static void setupClass() {
		WebDriverManager.edgedriver().setup();
	}

	@BeforeEach
	void setupTest() {
		driver = new EdgeDriver();
		driver.get(ExerciseURL.WAITS.getUrl());
		driver.manage().window().maximize();
	}

	@AfterEach
	void teardownTest() {
		driver.quit();
	}
	
	@Test
	void elementsLoadWithinTenSecondsTaskOne() {
		String expectedHeading = "Exercise one loaded";
		String loadTimeMatcher = "Loaded in: [0-9]*ms";
		WebElement heading = new WebDriverWait(driver, Duration.ofSeconds(10))
				.until(ExpectedConditions.presenceOfElementLocated(By.id("wait-target-1")));
		WebElement loadTime = new WebDriverWait(driver, Duration.ofSeconds(10))
				.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#wait-target-1+p")));
		
		Assertions.assertEquals(expectedHeading, heading.getText());
		Assertions.assertTrue(loadTime.getText().matches(loadTimeMatcher));
	}
	
	@Test
	void spinnerTestTask2() throws IOException {
		String expectedHeading = "Exercise one loaded";
		String loadTimeMatcher = "Loaded in: [0-9]*ms";
		WebElement loader = driver.findElement(By.id("ex1-loader"));
		
		// loader screenshot
		File loaderScreenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		loaderScreenshot.renameTo(new File("./loader.png"));
		if (loaderScreenshot.exists()) loaderScreenshot.delete();
		loaderScreenshot.createNewFile();
		
		// waits for heading and load time
		WebElement heading = new WebDriverWait(driver, Duration.ofSeconds(10))
				.until(ExpectedConditions.presenceOfElementLocated(By.id("wait-target-1")));
		WebElement loadTime = new WebDriverWait(driver, Duration.ofSeconds(10))
				.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#wait-target-1+p")));
		
		// should be no loaders present
		List<WebElement> loaders = driver.findElements(By.id("ex1-loader"));
		
		// screenshot content
		File contentScreenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		contentScreenshot.renameTo(new File("./content.png"));
		if (contentScreenshot.exists()) contentScreenshot.delete();
		contentScreenshot.createNewFile();
		
		// assertions
		Assertions.assertNotNull(loader);
		Assertions.assertEquals(0, loaders.size());
		Assertions.assertEquals(expectedHeading, heading.getText());
		Assertions.assertTrue(loadTime.getText().matches(loadTimeMatcher));
	}
}

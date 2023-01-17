package com.qa.selenium_4_sol_java.forms.exercise1;

import java.time.Duration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

import com.qa.selenium_4_sol_java.utils.ExerciseURL;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BingSearchFormEdgeTest {

	private WebDriver driver;
	
	@BeforeAll
	static void setupClass() {
		WebDriverManager.edgedriver().setup();
	}
	
	@BeforeEach
	void setupTest() {
		driver = new EdgeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
	}
	
	@AfterEach
	void teardownTest() {
		driver.quit();
	}
	
	@Test
	void bingSearchTaskOne() {
		// Arrange
		String searchTerm = "kittens";
		String expectedTitle = "kittens - Search";
		
		driver.get(ExerciseURL.FORMS.getUrl());
		WebElement searchInput = driver.findElement(By.name("q"));
		WebElement searchButton = driver.findElement(By.xpath("//button[text()='Search']"));
		
		// Act
		searchInput.sendKeys(searchTerm);
		searchButton.click();
		
		// Assert
		String actualSearchTerm = driver.findElement(By.name("q"))
										.getAttribute("value");

		Assertions.assertEquals(expectedTitle, driver.getTitle());
		Assertions.assertEquals(searchTerm, actualSearchTerm);
	}
	
	@Test
	void clearSearchTaskTwo() {
		// Arrange
		String searchTerm = "kittens";
		String expectedValue = "";
		
		driver.get(ExerciseURL.FORMS.getUrl());
		WebElement searchInput = driver.findElement(By.name("q"));
		WebElement searchButton = driver.findElement(By.xpath("//button[text()='Clear']"));
		
		// Act
		searchInput.sendKeys(searchTerm);
		searchButton.click();
		
		// Assert
		String actualValue = searchInput.getAttribute("value");
		Assertions.assertEquals(expectedValue, actualValue);
	}
	
	@Test
	void verifyValidationTaskThree() {
		// Arrange
		String expectedValue = "";
		
		driver.get(ExerciseURL.FORMS.getUrl());
		WebElement searchInput = driver.findElement(By.name("q"));
		WebElement searchButton = driver.findElement(By.xpath("//button[text()='Clear']"));
		
		// Act
		searchButton.click();
		
		// Assert
		String actualValue = searchInput.getAttribute("value");
		String actualUrl = driver.getCurrentUrl();
		Assertions.assertEquals(expectedValue, actualValue);
		Assertions.assertEquals(ExerciseURL.FORMS.getUrl(), actualUrl);
	}
}

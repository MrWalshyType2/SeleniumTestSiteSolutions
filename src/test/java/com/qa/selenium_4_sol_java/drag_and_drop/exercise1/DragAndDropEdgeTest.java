package com.qa.selenium_4_sol_java.drag_and_drop.exercise1;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;

import com.qa.selenium_4_sol_java.utils.ExerciseURL;
import com.qa.selenium_4_sol_java.utils.Helpers;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DragAndDropEdgeTest {

	private WebDriver driver;

	@BeforeAll
	static void setupClass() {
		WebDriverManager.edgedriver().setup();
	}

	@BeforeEach
	void setupTest() {
		driver = new EdgeDriver();
		driver.get(ExerciseURL.DRAG_N_DROP.getUrl());
		driver.manage().window().maximize();
	}

	@AfterEach
	void teardownTest() {
		driver.quit();
	}

	@Test
	void dragTextIntoBucketTest() throws Exception {
		WebElement draggable = driver.findElement(By.id("para-drop-1"));
		WebElement dropTarget = driver.findElement(By.id("drop-t1"));
		
		Helpers.dragAndDrop(draggable, dropTarget, driver);
		
		// Assert element was dragged and dropped
		Assertions.assertEquals(1, dropTarget.findElements(By.id("para-drop-1")).size());
	}
	
}

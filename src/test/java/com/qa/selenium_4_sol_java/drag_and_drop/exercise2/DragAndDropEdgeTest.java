package com.qa.selenium_4_sol_java.drag_and_drop.exercise2;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
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
	void fromTodoToDoing() throws Exception {
		WebElement todoColumn = driver.findElement(By.id("Todo"));
		WebElement todoItem = todoColumn.findElement(By.id("dnd-t1"));
		String expected = todoItem.getText();
		
		WebElement doingColumn = driver.findElement(By.id("Doing"));
		WebElement dropTarget = doingColumn.findElement(By.cssSelector("div[data-link='dnd-ex-2']"));
		
		// drag the todo item to the doing column
		new Actions(driver).moveToElement(dropTarget).perform();
		Helpers.dragAndDrop(todoItem, dropTarget, driver);
		
		// get the moved item and assert its text content
		WebElement doingItem = doingColumn.findElement(By.id("dnd-t1"));
		Assertions.assertEquals(expected, doingItem.getText());
		
		// assert that the item no longer exists in the todo column
		List<WebElement> todoItemSearchResults = todoColumn.findElements(By.id("dnd-t1"));
		Assertions.assertEquals(0, todoItemSearchResults.size());
	}
	
	@Test
	void fromDoingToDone() throws Exception {
		WebElement doingColumn = driver.findElement(By.id("Doing"));
		WebElement doingItem = doingColumn.findElement(By.id("dnd-t3"));
		String expected = doingItem.getText();
		
		WebElement doneColumn = driver.findElement(By.id("Done"));
		WebElement dropTarget = doneColumn.findElement(By.cssSelector("div[data-link='dnd-ex-2']"));
		
		// drag the todo item to the doing column
		new Actions(driver).moveToElement(dropTarget).perform();
		Helpers.dragAndDrop(doingItem, dropTarget, driver);
		
		// get the moved item and assert its text content
		WebElement doneItem = doneColumn.findElement(By.id("dnd-t3"));
		Assertions.assertEquals(expected, doneItem.getText());
		
		// assert that the item no longer exists in the todo column
		List<WebElement> doingItemSearchResults = doingColumn.findElements(By.id("dnd-t3"));
		Assertions.assertEquals(0, doingItemSearchResults.size());
	}
}

package com.qa.selenium_4_sol_java.tables.exercise1;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

import com.qa.selenium_4_sol_java.utils.ExerciseURL;

import io.github.bonigarcia.wdm.WebDriverManager;

public class EditableUserTableEdgeTest {

	private WebDriver driver;
	
	@BeforeAll
	static void setupClass() {
		WebDriverManager.edgedriver().setup();
	}

	@BeforeEach
	void setupTest() {
		driver = new EdgeDriver();
		driver.get(ExerciseURL.TABLES.getUrl());
		driver.manage().window().maximize();
	}
	
	@AfterEach
	void teardownTest() {
		driver.quit();
	}
	
	@Test
	void deleteUserTaskOne() {
		var usersTable = driver.findElement(By.cssSelector("#users-table"));
		var tableRows = usersTable.findElements(By.cssSelector("tbody > tr"));
		
		// fail on no users in table
		if (tableRows.size() == 0) {
			Assertions.fail("No users in table to delete for test");
			return;
		}
		
		// get first table row
		var firstUser = tableRows.get(0);
		var firstUserId = firstUser.getAttribute("id");
		
		// click delete button
		firstUser.findElement(By.cssSelector("td > button.bg-danger")).click();
		
		// after clicking delete, expect no element with the given id in the DOM
		Assertions.assertEquals(0, driver.findElements(By.id(firstUserId)).size());
	}
	
	@Test
	void editingUsersTaskTwo() {
		var expectedForename = "Bobby";
		WebElement usersTable = driver.findElement(By.cssSelector("#users-table"));
		List<WebElement> tableRows = usersTable.findElements(By.cssSelector("tbody > tr"));
		
		// fail on no users in table
		if (tableRows.size() == 0) {
			Assertions.fail("No users in table to delete for test");
			return;
		}
		
		// get first table row
		WebElement firstUser = tableRows.get(0);
		List<WebElement> dataCells = new ArrayList<>(firstUser.findElements(By.tagName("td")));
		
		// find and click edit button
		firstUser.findElement(By.cssSelector(".bg-warning")).click();
		
		// edit the forename field
		WebElement forenameInput = firstUser.findElement(By.name("forename"));
		forenameInput.clear();
		forenameInput.sendKeys(expectedForename);
		
		// click 'Save' button
		firstUser.findElement(By.cssSelector(".save-user-btn")).click();
		
		// get the data in the forename table cell
		var actualForename = dataCells.get(1).getText();
		Assertions.assertEquals(expectedForename, actualForename);
	}

	@Test
	void cancellingUserEditTaskThree() {
		var expectedForename = "Bob";
		WebElement usersTable = driver.findElement(By.cssSelector("#users-table"));
		List<WebElement> tableRows = usersTable.findElements(By.cssSelector("tbody > tr"));
		
		// fail on no users in table
		if (tableRows.size() == 0) {
			Assertions.fail("No users in table to delete for test");
			return;
		}
		
		// get first table row
		WebElement firstUser = tableRows.get(0);
		List<WebElement> dataCells = new ArrayList<>(firstUser.findElements(By.tagName("td")));
		WebElement controlButtonsTd = dataCells.remove(dataCells.size() - 1); // remove the control buttons
		
		// find and click edit button
		firstUser.findElement(By.cssSelector(".bg-warning")).click();
		
		// edit the forename field
		WebElement forenameInput = firstUser.findElement(By.name("forename"));
		forenameInput.clear();
		forenameInput.sendKeys("Bobby");
		
		// click 'Cancel' button
		firstUser.findElement(By.cssSelector(".cancel-edit-user-btn")).click();
		
		// get the data in the forename table cell
		var actualForename = dataCells.get(1).getText();
		Assertions.assertEquals(expectedForename, actualForename);
	}

	@Test
	void cancellingUserEditTaskFour() {
		var expectedForename = "Bob";
		WebElement usersTable = driver.findElement(By.cssSelector("#users-table"));
		List<WebElement> tableRows = usersTable.findElements(By.cssSelector("tbody > tr"));
		
		// fail on no users in table
		if (tableRows.size() == 0) {
			Assertions.fail("No users in table to delete for test");
			return;
		}
		
		// get first table row
		WebElement firstUser = tableRows.get(0);
		WebElement secondUser = tableRows.get(1);
		List<WebElement> dataCells = new ArrayList<>(firstUser.findElements(By.tagName("td")));
		
		// find and click edit button
		WebElement editButton = firstUser.findElement(By.cssSelector(".bg-warning"));
		editButton.click();
		
		// edit the forename field
		WebElement forenameInput = firstUser.findElement(By.name("forename"));
		forenameInput.clear();
		forenameInput.sendKeys("Bobby");
		
		// click 'Edit' button of second user
		secondUser.findElement(By.cssSelector(".bg-warning")).click();
		
		// get the data in the forename table cell
		var actualForename = dataCells.get(1).getText();
		Assertions.assertEquals(expectedForename, actualForename);
	}
}

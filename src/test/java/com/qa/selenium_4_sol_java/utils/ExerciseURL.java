package com.qa.selenium_4_sol_java.utils;

public enum ExerciseURL {
	DRAG_N_DROP("http://localhost:3000/exercises/dragdrop"), 
	FORMS("http://localhost:3000/exercises/forms"), 
	TABLES("http://localhost:3000/exercises/tables"), 
	WAITS("http://localhost:3000/exercises/waits");
	
	private final String url;
	
	private ExerciseURL(String url) {
		this.url = url;
	}
	
	public String getUrl() {
		return this.url;
	}
}

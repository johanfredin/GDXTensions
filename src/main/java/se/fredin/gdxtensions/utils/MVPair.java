package se.fredin.gdxtensions.utils;

public class MVPair {

	public String message;
	public String value;
		
	public MVPair(String message, Object value) {
		this.message = message;
		this.value = value.toString();
	}
	
}

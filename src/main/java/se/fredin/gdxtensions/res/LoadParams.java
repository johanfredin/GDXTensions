package se.fredin.gdxtensions.res;

public class LoadParams {
	
	public String path;
	
	@SuppressWarnings("rawtypes")
	public Class clazz;
	
	@SuppressWarnings("rawtypes")
	public LoadParams(String path, Class clazz) {
		this.path = path;
		this.clazz = clazz;
	}
}
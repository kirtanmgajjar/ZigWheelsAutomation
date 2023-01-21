package utilities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.util.Properties;

//Class to read data from properties file
public class PropertiesFileReader
{
	private Properties properties;
	
	//Class Constructor
	public PropertiesFileReader(String filePath) throws Exception
	{
		BufferedReader reader;
		reader = new BufferedReader(new FileReader(filePath));
		properties = new Properties();
		properties.load(reader);
		reader.close();
	}
	
	//Class Constructor
	public PropertiesFileReader(InputStream fis) throws Exception
	{
		properties = new Properties();
		properties.load(fis);
		fis.close();
	}
	
	//Method to get the browser name using "browser" key
	public String getBrowserName() throws Exception
	{
		String name = properties.getProperty("browser");
		return name;
	}
	
	//Method to get the baseUrl name using "baseUrl" key
	public String getBaseUrl() throws Exception
	{
		String url = properties.getProperty("baseUrl");
		return url;
	}
	
}

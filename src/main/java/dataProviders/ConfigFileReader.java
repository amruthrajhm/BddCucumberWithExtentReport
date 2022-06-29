package dataProviders;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import enums.DriverType;
//reads the configuration file

public class ConfigFileReader {
	private Properties properties;
	private final String propertyFilePath= "Configs//Configuration.properties";
	
	public ConfigFileReader(){
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(propertyFilePath));
			properties = new Properties();
			try {
				properties.load(reader);
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("Configuration.properties not found at " + propertyFilePath);
		}		
	}
	
	public String getDriverPath(){
		String driverPath = properties.getProperty("driverPath");
		if(driverPath!= null) return driverPath;
		else throw new RuntimeException("driverPath not specified in the Configuration.properties file.");		
	}
	
	public long getImplicitlyWait() {		
		String implicitlyWait = properties.getProperty("implicitlyWait");
		if(implicitlyWait != null) return Long.parseLong(implicitlyWait);
		else throw new RuntimeException("implicitlyWait not specified in the Configuration.properties file.");		
	}
	
	public  int getWaitSeconds(String waitType)
	{
		String iWait = properties.getProperty(waitType);
		return Integer.valueOf(iWait);
	}
	
	public String getApplicationUrl() {
		System.out.println(properties.getProperty("url"));
		String url = properties.getProperty("url");
		if(url != null) return url;
		else throw new RuntimeException("url not specified in the Configuration.properties file.");
	}
	
	
	//returns the type of browser ur testing
	public DriverType getBrowser() {
		String browserName = properties.getProperty("browser");
		if(browserName == null || browserName.equals("chrome")) return DriverType.CHROME;
		else if(browserName.equalsIgnoreCase("firefox")) return DriverType.FIREFOX;
		else throw new RuntimeException("Browser Name Key value in Configuration.properties is not matched : " + browserName);
	}
	
	public boolean getBrowserWindowSize() {
		String windowMaximize = properties.getProperty("wndowMaximize");
		if(windowMaximize != null) return Boolean.valueOf(windowMaximize);
		return true;	
	}
	

	public String getReportConfigPath() {
		//"C:\\Users\\admin\\eclipse-workspace\\bddCucumber\\"
	String fileName=System.getProperty("user.dir")+"\\Configs\\"+"Configuration.properties";
	Properties Prop =new Properties();
	try {
		Prop.load(new FileInputStream(new File(fileName))); //load the properties of file
	} catch (Exception e) {
		e.printStackTrace();
	}//get the file
	
		String reportConfigPath = Prop.getProperty("reportConfigPath");
		System.out.println(reportConfigPath);	
		if(reportConfigPath!= null) return reportConfigPath;
		else throw new RuntimeException("Report Config Path not specified in the Configuration.properties file for the Key:reportConfigPath");		
	
	}
	
	
	public Properties readPropertyFileData() 
	{
		String fileName=System.getProperty("user.dir")+"\\Configs\\"+"Configuration.properties";
		Properties Prop =new Properties();
		FileReader iReader=null;
		try {
			 iReader=new FileReader(new File(fileName));
			Prop.load(new BufferedReader(iReader)); //load the properties of file
			//url=Prop.getProperty("url"); //get the url assign it to public variable
				
		} 
		catch(FileNotFoundException fnfe) {
	        fnfe.printStackTrace();
	     }
		catch(IOException ioe) {
	        ioe.printStackTrace();
	     }
		finally {
				try {
					iReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}	
	     }

		return Prop;
	}
	

}

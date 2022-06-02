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
	String dir=	System.getProperty("user.dir");
	Properties Prop =new Properties();
	String fileName=dir+"\\Configs\\"+"Configuration.properties";//"C:\\Users\\admin\\eclipse-workspace\\bddCucumber\\"
	FileInputStream iFile = null;
	
	try {
		iFile = new FileInputStream(new File(fileName));
		Prop.load(iFile); //load the properties of file
	} catch (Exception e) {
		e.printStackTrace();
	}//get the file
	finally {
		try {
			iFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
		String reportConfigPath = Prop.getProperty("reportConfigPath");
		System.out.println(reportConfigPath);
		
		
		if(reportConfigPath!= null) return reportConfigPath;
		else throw new RuntimeException("Report Config Path not specified in the Configuration.properties file for the Key:reportConfigPath");		
	
	}
	
	
	public Properties readPropertyFileData() 
	{
		Properties Prop =new Properties();
		String dir=System.getProperty("user.dir");
		String fileName=dir+"\\Configs\\"+"Configuration.properties";//"C:\\Users\\admin\\eclipse-workspace\\bddCucumber\\"
		FileInputStream iFile = null;
		try {
			iFile = new FileInputStream(new File(fileName));//get the file
			Prop.load(iFile); //load the properties of file
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
					iFile.close();
				} catch (IOException e) {
					e.printStackTrace();
				}	
	     }

		return Prop;
	}
	

}

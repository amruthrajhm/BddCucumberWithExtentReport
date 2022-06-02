package managers;
import exceptions.UserDefinedExceptions;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ObjectRepositoryManager 
{
	private static  Properties objectRepoFile;
	private static final Properties propObj=new Properties();
	
	public static Properties getObjectRepository() throws IOException
	{
		
		if (objectRepoFile!=null) {
			return objectRepoFile;
		}
		else {
			throw new IOException("Object Repository Is null, use setObjectRepository before Get");
		}
	
		
	}
	
	public static Properties setObjectRepository(String FileWithPath)
	{
		String dir=	System.getProperty("user.dir");
		//Properties Prop =new Properties();
		String fileName=dir+"\\src\\test\\resources\\ObjectRepositories\\"+FileWithPath; 
		FileInputStream iFile = null;
		//
		try {
			 iFile = new FileInputStream(new File(fileName));
			propObj.load(iFile);//load the file to propObj
			objectRepoFile=propObj;
		} 
		catch (FileNotFoundException e) {e.printStackTrace();} 
		catch (IOException e) {e.printStackTrace();}
		
finally {
	try { iFile.close(); } catch (IOException e) {	e.printStackTrace();}
		}
		return objectRepoFile;
		
	}
}

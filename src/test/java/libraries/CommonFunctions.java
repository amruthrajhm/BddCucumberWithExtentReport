package libraries;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class CommonFunctions {
	public static String getRandomNumber(int length)
	{
		String randomNum="";
		int max=9,min=0;
		for (int i = 0; i < length; i++) {
			int temp=(int)Math.floor(Math.random()*(max-min+1)+min);
			//System.out.println(Math.floor(Math.random()*1));
			randomNum=randomNum+temp;
		}
		return randomNum;
	}
	
	public static String getAlphaNumericNumber(int length)
	{
		int min=0;
		String chars="ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvwxyz";
		String myStr="";
		for (int i = 0; i <length; i++) {
			int temp=(int)Math.floor(Math.random()*(chars.length()-min+1)+min);
			myStr=myStr+chars.charAt(temp);
		}
		
		
		return myStr;
	}
	
	public static String getFileText(String filePath)
	{
		String iText="";
		try {
			List<String> temp= Files.readAllLines(Paths.get(filePath));
			 iText=temp.stream().collect(Collectors.joining(System.lineSeparator()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return iText;
		
	}
	public static void main(String[] args) {
		System.out.println( getRandomNumber(15));
		System.out.println( getAlphaNumericNumber(15));
		System.out.println(getFileText("C:\\Users\\admin\\git\\BddCucumberWithExtentReport\\src\\test\\java\\libraries\\DataHandler.java"));
	}

}

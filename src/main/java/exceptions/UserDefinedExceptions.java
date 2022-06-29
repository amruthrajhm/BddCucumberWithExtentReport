package exceptions;

public class UserDefinedExceptions extends Exception {
	
	
public UserDefinedExceptions(String Msg) {
	System.out.println("Object Repository Is null, use setObjectRepository before Get");
}

}

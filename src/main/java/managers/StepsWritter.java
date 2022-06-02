package managers;

import io.cucumber.java.Scenario;

//this is used to write the steps performed in execution
//call StepsWritter.writeSteps 'to write log to current scenario

//info: scenario object created in Hooks File initDriver method
public class StepsWritter {
	public static Scenario  currentScenario;
	public static void SetScenario(Scenario scn)
	{
		currentScenario=scn;
	}
	
	public static Scenario getScenario()
	{
		return currentScenario;
	}
	
	public static void writeSteps( String Description)
	{
		StepsWritter.currentScenario.log(Description);
	}

	

}

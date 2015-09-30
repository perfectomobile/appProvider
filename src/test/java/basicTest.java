package test.java;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.DriverCommand;
import org.openqa.selenium.remote.RemoteExecuteMethod;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public abstract  class basicTest {

	RemoteWebDriver _driver;

	public basicTest() {

	}

	public void setUpDriver(RemoteWebDriver  driver )
	{
		_driver = driver;
	}

	@DataProvider(name = "Devices", parallel = true)
	public static Iterator<Object[]> getDesiredCapabilities() {

		List<String> devices = util.getDevieList();

		List<Object[]> objects = new ArrayList();
		if (!(devices.isEmpty())){
			for (String object : devices) {
				objects.add(new Object[] { object} );
			}
		}                             
		return objects.iterator();
	}

	public abstract void beforeTest() throws Exception ;
	public abstract void execTest(RemoteWebDriver driver) throws Exception ;
	public abstract void endTest(RemoteWebDriver driver)  ;

	public void switchToContext(RemoteWebDriver driver, String context) {
		RemoteExecuteMethod executeMethod = new RemoteExecuteMethod(driver);
		Map<String,String> params = new HashMap<String,String>();
		params.put("name", context);
		executeMethod.execute(DriverCommand.SWITCH_TO_CONTEXT, params);
	}

	public String getCurrentContextHandle(RemoteWebDriver driver) {                         
		RemoteExecuteMethod executeMethod = new RemoteExecuteMethod(driver);
		String context =  (String) executeMethod.execute(DriverCommand.GET_CURRENT_CONTEXT_HANDLE, null);
		return context;
	}

	public List<String> getContextHandles(RemoteWebDriver driver) {                           
		RemoteExecuteMethod executeMethod = new RemoteExecuteMethod(driver);
		List<String> contexts =  (List<String>) executeMethod.execute(DriverCommand.GET_CONTEXT_HANDLES, null);
		return contexts;
	}
}

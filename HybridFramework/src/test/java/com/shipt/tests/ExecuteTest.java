package com.shipt.tests;

import java.lang.reflect.Method;

import org.testng.ITest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.shipt.tests.DataProviders;

public class ExecuteTest extends TestBase implements ITest {

    private String testName = "";

    public String getTestName() {
	return testName;
    }

    private void setTestName(String param) {
	testName = param;
    }

    @BeforeMethod(alwaysRun = true)
    public void beforeMethod(Method method, Object[] parameters) {
	setTestName(method.getName());
	Override a = method.getAnnotation(Override.class);
	String testCaseId = (String) parameters[a.id()];
	if (testCaseId != null && testCaseId.length() != 0) {
	setTestName(testCaseId);
	} else {
	    setTestName((String) parameters[a.id()]);
	}
    }

    // TODO add Test Case names
    @Override
    @Test(dataProvider = "UIData", dataProviderClass = DataProviders.class)
    public void uiTest(String testcaseName, String keyword, String objectName, String objectType, String value)
	    throws Exception {
	ivRun(testcaseName);
	ivRead(keyword, objectName, objectType, value);

    }

}

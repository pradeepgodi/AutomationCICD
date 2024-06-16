package pgodi.tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import pgodi.TestComponents.BaseTest;
import pgodi.TestComponents.Retry;


public class ErrorValidation extends BaseTest{

	@Test(groups= {"ErrorHandling"},retryAnalyzer=Retry.class)
	public void LoginErrorValidation() throws IOException, InterruptedException {

		String username= "gpradeep.srs@gmail.com";
		String password = "Paddi_1984$";
	
		lp.loginToApplication( username, password);
		System.out.println("checking for Login Error ="+lp.getErrorMessage());
		Assert.assertEquals("Incorrect email or password.", lp.getErrorMessage());
	

	}

}


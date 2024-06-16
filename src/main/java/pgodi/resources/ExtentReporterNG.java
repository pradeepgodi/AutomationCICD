package pgodi.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
	
	static ExtentReports extent ;
	
	public static ExtentReports getReportObject() {
		
		//ExtentReports , ExtentSparkReporter are 2 important classes to generate extent reports
		
		String path = System.getProperty("user.dir") + "\\reports\\index.html";	
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Web Automation Results");
		reporter.config().setDocumentTitle("Automation Test Results");
		
		extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "pgodi");
		
		return extent;
		
		
		
	}
	
}

package TestUtilities.Listeners;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.stc.expedia.basetest.basetest;

import io.qameta.allure.Attachment;

public class TestListener extends basetest implements ITestListener {
	 private static String getTestMethodName(ITestResult iTestResult) {
	        return iTestResult.getMethod().getConstructorOrMethod().getName();
	    }

	    //Text attachments for Allure
	    @Attachment(value = "Page screenshot", type = "image/png")
	    public byte[] saveScreenshotPNG ( WebDriver  driver) {
	        return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
	    }
	    @Attachment(value = "{0}", type = "text/plain")
	    public static String saveTextLog (String message) {
	        return message;
	    }

	    //HTML attachments for Allure
	    @Attachment(value = "{0}", type = "text/html")
	    public static String attachHtml(String html) {
	        return html;
	    }
	    @Override
	    public void onStart(ITestContext iTestContext) {
	        System.out.println("I am in onStart method " + iTestContext.getName());
	        iTestContext.setAttribute("WebDriver", this.driver);
	    }
	    
	    @Override
	    public void onFinish(ITestContext iTestContext) {
	        System.out.println("I am in onFinish method " + iTestContext.getName());
	        }
	    @Override
	    public void onTestStart(ITestResult iTestResult) {
	        System.out.println("I am in onTestStart method " +  getTestMethodName(iTestResult) + " start");
	    }
	    @Override
	    public void onTestSuccess(ITestResult iTestResult) {
	        System.out.println("I am in onTestSuccess method " +  getTestMethodName(iTestResult) + " succeed");
	    }
	    
	    @Override
	    public void onTestFailure(ITestResult iTestResult) {
	        System.out.println("I am in onTestFailure method " +  getTestMethodName(iTestResult) + " failed");

	        //Get driver from BaseTest and assign to local webdriver variable.
	        Object testClass = iTestResult.getInstance();
	        WebDriver driver = ((basetest) testClass).getDriver();

	        //Allure ScreenShotRobot and SaveTestLog
	        if (driver instanceof  WebDriver) {
	            System.out.println("Screenshot captured for test case:" + getTestMethodName(iTestResult));
	            saveScreenshotPNG(driver);
	        }

	        //Save a log on allure.
	        saveTextLog(getTestMethodName(iTestResult) + " failed and screenshot taken!");

	        //Take base64Screenshot screenshot for extent reports
	        String base64Screenshot = "data:image/png;base64,"+((TakesScreenshot)driver).
	                getScreenshotAs(OutputType.BASE64);

	        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	      //The below method will save the screen shot in d drive with name "screenshot.png"
	      String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
	      File screenShotName = new File("src\\test\\java\\ErrorScreenShots\\"+timeStamp+".png");
	      try {
			FileUtils.copyFile(scrFile, screenShotName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      Reporter.log("<br><img src='"+screenShotName+"' height='300' width='300'/><br>");

         Reporter.log("<a href='"+ screenShotName.getAbsolutePath() + "'> <img src='"+ screenShotName.getAbsolutePath() + "' height='100' width='100'/> </a>");
        
	      String filePath = screenShotName.toString();
	     // String path = "<img src="\"file://"" alt="\"\"/" />";
	      //Reporter.log(path);
	    }
	    @Override
	    public void onTestSkipped(ITestResult iTestResult) {
	        System.out.println("I am in onTestSkipped method "+  getTestMethodName(iTestResult) + " skipped");
	       
	    }

	    @Override
	    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
	        System.out.println("Test failed but it is in defined success ratio " + getTestMethodName(iTestResult));
	    }
}

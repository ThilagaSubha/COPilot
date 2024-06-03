package core;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
/*
Test
Test
This file is for Negative Test of Login with Unregistered Email ID
 */
public class NegativeFlow
{
    public static void main(String[] args) {
    // Set desired capabilities for the Android device
    DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
  /*  desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "android");
    desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "14");
    desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel_3a_API_34_extension_level_7_x86_64");
    desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
    desiredCapabilities.setCapability(MobileCapabilityType.APP, "/home/datasirpi/Downloads/app-release 26.apk");*/

    // Create an instance of the AppiumDriver
    AppiumDriver<MobileElement> driver = null;
    try {
        driver = new AndroidDriver<>(new URL("http://localhost:4723/wd/hub"), desiredCapabilities);

        // Wait for the App to OPen (explicit wait)
        WebDriverWait wait = new WebDriverWait(driver, 30);
        // Locate the SOC Logo to verify the login was successful
        MobileElement socLogo = driver.findElementByXPath("//android.widget.ImageView");
        boolean isLogoDisplayed = socLogo.isDisplayed();
        if (isLogoDisplayed) {
            System.out.println("Login successful and SOC Logo is displayed.");
        } else {
            System.out.println("Login failed or SOC Logo is not displayed.");
        }
        // Find and click the Login Field using class name and index
        MobileElement loginEditText = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//android.widget.EditText)[1]")));
        loginEditText.click();

        // Adding a small delay
        Thread.sleep(1000);

        // Clear the text box
        loginEditText.clear();

        // Enter the wrong login email
        loginEditText.sendKeys("unregisteredemail@example.com");
        System.out.println("Wrong login email ID is entered");

        // Adding a 5-second delay before interacting with fields
        Thread.sleep(5000);

        // Find and click the EditText box for Password using class name and index
        MobileElement passwordEditText = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//android.widget.EditText)[2]")));
        passwordEditText.click();

        // Adding a small delay
        Thread.sleep(1000);

        // Clear the text box
        passwordEditText.clear();

        // Enter the wrong password
        passwordEditText.sendKeys("Testing@1234567890");
        System.out.println("Wrong password is entered");

        // Hide the mobile keypad
        driver.hideKeyboard();

        // Adding a 5-second delay before interacting with fields
        Thread.sleep(5000);

        // Locate and click the 'Submit' button
        MobileElement submitButton = driver.findElementByXPath("//android.widget.Button[@content-desc='SUBMIT']");
        submitButton.click();
        System.out.println("Submit Button is Clicked");
        Thread.sleep(5000);

        // Wait for the error message to appear
        MobileElement errorMessage = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.view.View[@content-desc='Invalid User Credential']")));
        boolean isErrorDisplayed = errorMessage.isDisplayed();
        if (isErrorDisplayed) {
            System.out.println("Error message displayed: Invalid User Credential.");
        } else {
            System.out.println("Error message not displayed.");
        }




    } catch (MalformedURLException e)
        {
        e.printStackTrace();
    } catch (Exception e) {
        e.printStackTrace();
    }
}
}
//testing


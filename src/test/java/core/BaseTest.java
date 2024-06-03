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
// Login Flow //
public class BaseTest
{
    public static void main(String[] args) {
    // Set desired capabilities for the Android device
    DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
    desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "android");
    desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "14");
    desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel_3a_API_34_extension_level_7_x86_64");
    desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
    desiredCapabilities.setCapability(MobileCapabilityType.APP, "/home/datasirpi/Downloads/app-release 26.apk");

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
        Thread.sleep(5000);



        // Clear the text box
        loginEditText.clear();

        // Enter the login email
        loginEditText.sendKeys("thilagasubha.s@datasirpi.com");
        System.out.println("Login Email Id is entered");
        Thread.sleep(5000);

        // Find and click the EditText box for Password using class name and index
        MobileElement passwordEditText = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//android.widget.EditText)[2]")));
        passwordEditText.click();
        Thread.sleep(5000);


        passwordEditText.clear();

        // Enter the password
        passwordEditText.sendKeys("Data@1234567890");
        System.out.println("Password Data@1234567890 is entered");

        // Hide the mobile keypad
        driver.hideKeyboard();


                // Locate and click the 'Submit' button
        MobileElement submitButton = driver.findElementByXPath("//android.widget.Button[@content-desc='SUBMIT']");
        submitButton.click();
        System.out.println("Submit Button is Clicked");
        Thread.sleep(5000);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.view.View[@content-desc='Please Verify Account']")));
        System.out.println("OTP page is displayed");
        Thread.sleep(5000);

        // Enter OTP
        String otp = "362510";
        for (int i = 0; i < otp.length(); i++) {
            MobileElement otpEditText = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//android.widget.EditText)[" + (i + 1) + "]")));
            otpEditText.click();
            otpEditText.sendKeys(Character.toString(otp.charAt(i)));
            System.out.println("Entered " + otp.charAt(i) + " in field with index " + i);
            Thread.sleep(500);
        }
        System.out.println("OTP entered successfully");

        // Confirm we are on the Dashboard page
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.view.View[@content-desc='Dashboard']")));
        System.out.println("Dashboard page is displayed.");
        Thread.sleep(5000);


        //Navigate to Hamburger Menu
        MobileElement hamburgerMenu = (MobileElement) wait.until(ExpectedConditions.elementToBeClickable(By.className("android.widget.Button")));
        hamburgerMenu.click();
        System.out.println("Clicked the hamburger menu.");
        Thread.sleep(5000);

        //Navigate to Incident Response
        MobileElement incidentResponse = (MobileElement) wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.view.View[@content-desc=\"Incident Response\"]")));
        incidentResponse.click();
        System.out.println("Clicked the Incident Response menu.");
        Thread.sleep(5000);

        //Navigate to ALert Submenu
        MobileElement alertSubMenu = (MobileElement) wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.view.View[@content-desc=\"Alerts\"]")));
        alertSubMenu.click();
        System.out.println("Clicked the Alert Sub menu.");
        Thread.sleep(5000);








    } catch (MalformedURLException e)
        {
        e.printStackTrace();
    } catch (Exception e) {
        e.printStackTrace();
    }
}
}


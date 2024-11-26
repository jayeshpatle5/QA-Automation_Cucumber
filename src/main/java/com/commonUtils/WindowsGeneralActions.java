package com.commonUtils;

import com.online.driver.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

    public class WindowsGeneralActions {
        public static WebElement waitForByElement(By element, long timeOut){
            WebDriverWait wt = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeOut));
            new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeOut));
            // wt.until(ExpectedConditions.)
            return wt.until(ExpectedConditions.visibilityOf(DriverManager.getDriver().findElement(element)));
    }

    public static void userWait(long time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    // Method to highlight an element
    public static void highlightElement(WebDriver driver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].style.border='3px solid red'", element);
    }


}

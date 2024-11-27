package com.commonUtils;

import com.commonUtils.enums.WaitStrategy;
import com.online.driver.DriverManager;
import com.online.driver.ExpliciteWaitFactory;
import com.online.pages.BasePage;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class WindowsGeneralActions {

        private static final Logger logger = LogManager.getLogger(WindowsGeneralActions.class);

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

    protected void click(By by, WaitStrategy waitStrategy, String elementName)  {
        ExpliciteWaitFactory.performExplicteWait(waitStrategy, by).click();
    }



    protected void enterText(By by, WaitStrategy waitStrategy, String value,String elementName){
        ExpliciteWaitFactory.performExplicteWait(waitStrategy, by).sendKeys(value);
    }

    protected String getPageTitle() {
        logger.info(DriverManager.getDriver().getTitle()+" title found successfully");
        return DriverManager.getDriver().getTitle();
    }

    protected String getCurrentpageUrl(){
        logger.info(DriverManager.getDriver().getCurrentUrl() + " Current Url found successfully");
        return DriverManager.getDriver().getCurrentUrl();
    }

    //By using getShadowRoot() method;
    public  static WebDriver getDriverByShadowRoot(){
        return DriverManager.getDriver();
    }
    public static WebElement findElementByShadowRootMethod(WebDriver driver, By parentSelector, By childSelector) {

        return findElementByShadowRootMethod(driver,parentSelector,childSelector,40);
    }

    static List<WebElement> getShadowElementsWithWait(WebDriver driver, WebElement host, By selector, int waitTime) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(waitTime));

        wait.until(d -> ((JavascriptExecutor) driver).executeScript("return arguments[0].shadowRoot", host) != null);

        SearchContext shadowHost = host.getShadowRoot();

        return shadowHost.findElements(selector);
    }

    static WebElement getShadowWebElementWithWait(WebDriver driver, WebElement host, By selector, int waitTime) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(waitTime));

        wait.until(d -> ((JavascriptExecutor) driver).executeScript("return arguments[0].shadowRoot", host) != null);

        SearchContext shadowHost = host.getShadowRoot();

        return shadowHost.findElement(selector);
    }

    public static String getTextElementByShadowRoot(WebDriver driver, By parentSelector, By childSelector,String elementName)  {
        WebElement element = findElementByShadowRootMethod(driver, parentSelector, childSelector);
        WindowsGeneralActions.userWait(3000);
        if(!element.getText().isBlank()){
            logger.info(element.getText()+" captured from element "+elementName);
        }else {
            logger.error(element.getText()+" Not captured from element "+elementName);
        }
        return element.getText();
    }


    public static void clickElementByShadowRoot(WebDriver driver, By parentSelector, By childSelector,String elementName) {
        WebElement element = findElementByShadowRootMethod(driver, parentSelector, childSelector);
        element.click();
        logger.info("Clicked element "+elementName);
    }

    public static void enterTextByShadowRoot(WebDriver driver, By parentSelector, By childSelector,String text,String elementName) {
        WebElement element = findElementByShadowRootInteractMethod(driver, parentSelector, childSelector,20);
        logger.info(text+" entered Text into "+elementName);
        element.sendKeys(text);
    }
    public static void enterTextByUsingActionShadowRoot(WebDriver driver, By parentSelector, By childSelector,String text,String elementName) {
        WebElement element = findElementByShadowRootInteractMethod(driver, parentSelector, childSelector,20);
        element.click();
        Actions action =new Actions(driver)	;
        action.sendKeys(text).build().perform();
        logger.info(text+" entered into "+elementName+" by using Action Class");
    }

    public static Boolean isDisplayedByShadowRoot(WebDriver driver, By parentSelector, By childSelector,String elementName) {
        WebElement element = findElementByShadowRootMethod(driver, parentSelector, childSelector);

        Boolean flag= element.isDisplayed();
        if(flag){
            logger.info(elementName+ " element is displayed  "+element.isDisplayed());
        }else{
            logger.error(elementName+" Element is not displayed  "+element.isDisplayed());
        }
        return flag;
    }

    public static void scrollBy(WebDriver driver, int x, int y) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(arguments[0], arguments[1]);", x, y);
        logger.info("Scrolled the Element up to X and Y axes is "+x+" "+y);
    }

    public static String getShadowrootAttribute(WebDriver driver, By parentSelector, By childSelector,String elementName){
        WebElement element = findElementByShadowRootMethod(driver, parentSelector, childSelector);
        logger.info(elementName+"captured color code is "+element.getCssValue("color"));
        return element.getCssValue("color");
    }

    public static WebElement findElementByShadowRootMethod(WebDriver driver, By parentSelector, By childSelector, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));

        // Wait for the parent element to be present
        WebElement parent = wait.until(ExpectedConditions.presenceOfElementLocated(parentSelector));

        // Use a custom ExpectedCondition to wait for the child element within the shadow root
        WebElement child = wait.until(new ExpectedCondition<WebElement>() {
            @Override
            public WebElement apply(WebDriver driver) {
                SearchContext shadowRoot = parent.getShadowRoot();

                return shadowRoot.findElement(childSelector);

            }

            @Override
            public String toString() {
                return "child element located by " + childSelector + " inside shadow root of " + parentSelector;
            }
        });
        return child;
    }

    public static WebElement findElementByShadowRootInteractMethod(WebDriver driver, By parentSelector, By childSelector, int timeoutInSeconds) {
        //DriverManager.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        // Wait for the parent element to be present
        WebElement parent = wait.until(ExpectedConditions.elementToBeClickable(parentSelector));
        // Use a custom ExpectedCondition to wait for the child element within the shadow root
        return wait.until(new ExpectedCondition<>() {
            @Override
            public WebElement apply(WebDriver driver) {
                SearchContext shadowRoot = parent.getShadowRoot();
                return shadowRoot.findElement(childSelector);
            }
            @Override
            public String toString() {
                return "child element located by " + childSelector + " inside shadow root of " + parentSelector;
            }
        });
    }

    public static String getClipboardContents() {
        String result = "";
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        try {
            result = (String) clipboard.getData(DataFlavor.stringFlavor);
        } catch (UnsupportedFlavorException | IOException e) {
            e.printStackTrace();
        }
        if(result!=null) {
            logger.info(result + " copied to result variable");
        }else{
            logger.error(result + " not copied to result variable");

        }
        return result;
    }
    public static String getTextByJavaScriptExecutor(WebDriver driver, By parentSelector, By childSelector) {
        List<String> contentTitles = new ArrayList<>();
        List<WebElement> element = getShadowElementsWithWait(driver, driver.findElement(parentSelector), childSelector, 20);
        System.out.println(element.size());
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String title = (String) js.executeScript("return arguments[0].innerText;", element.get(1));//we are capturing second element title in the list
        logger.info("get the text using JavaScript Executor "+title);
        return title;
    }

}

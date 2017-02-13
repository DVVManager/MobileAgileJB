package org.agileboard.pages;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.agileboard.appdriver.DriverProvider;
import org.agileboard.common.CommonManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DriverCommand;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import java.util.HashMap;
import java.util.List;


/**
 * Created by Administrator on 1/29/2017.
 */
public class BasePage {

    protected Logger pageLogger = Logger.getLogger(BasePage.class);

    private DriverProvider provider;

    public BasePage(DriverProvider  driverProvider) {
        provider=driverProvider;
        PageFactory.initElements(getDriver(), this);
    }

    protected AndroidDriver getDriver() {
        return provider.getDriver();
    }
    protected WebDriverWait getWaitDriver(){
        return provider.getWaitDriver();
    }

    //common selenium wrapped methods
    protected void clearAndType(WebElement inputElement, String inputText) {
        pageLogger.info("Typing text "+inputText+" to field "+inputElement.getTagName()+inputElement.getText());
        inputElement.clear();
        CommonManager.waitSeconds(1);
        inputElement.sendKeys(inputText);

    }

    protected void clearFieldNonJS(WebElement element) {
        pageLogger.info("Cleaning field "+ element.getTagName()+ element.getText()+" with JS");
        element.sendKeys(Keys.CONTROL + "A" + Keys.DELETE);
    }

    protected void typeText(WebElement element, String text) {
        element.sendKeys(text);
    }

    protected void click(WebElement clickElement) {
        getWaitDriver().until(ExpectedConditions.elementToBeClickable(clickElement));
        clickElement.click();
    }

    protected void clickIfVisible(WebElement element) {
        pageLogger.info("Click element "+element.getTagName()+element.getText()+" if visible");
        getWaitDriver().until(ExpectedConditions.visibilityOf(element));
        element.click();
    }

    protected void clickWithJS(WebElement element){

        pageLogger.info("Click "+element.getTagName()+element.getText());
        getWaitDriver().until(ExpectedConditions.visibilityOf(element));
        executeJSCommand("arguments[0].click();", element);

    }

    protected boolean webElementIsEnabled(WebElement element) {
        pageLogger.info("Verifying element "+element.getTagName()+element.getText()+" to be enabled");
        return element.isEnabled();
    }

    protected void waitElementToBeClickable(String xpath) {
        getWaitDriver().until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
    }

    protected void moveToElementAndClick(WebElement element){
        Actions acts=new Actions(getDriver());
        acts.moveToElement(element).click().build().perform();
    }
    protected void moveToLocationAndClick(WebElement element){
        int x=element.getLocation().getX();
        int y=element.getLocation().getY();
        Actions acts=new Actions(getDriver());
        acts.moveByOffset(x, y).contextClick().build().perform();

    }
    protected void submit(WebElement element) {
        getWaitDriver().until(ExpectedConditions.elementToBeClickable(element));
        element.submit();
    }

    protected WebElement getElementBy(By by){
        return getDriver().findElement(by);
    }

    protected List<WebElement> getElementsBy(By by){
        return getDriver().findElements(by);
    }

    protected void cancelAlertPopUp() {
        String alertString = "";
        try {
            Alert alert = getDriver().switchTo().alert();
            alertString = alert.getText();
            alert.dismiss();
            pageLogger.info(alertString + " - Alert text");
        } catch (NoAlertPresentException e) {
            pageLogger.info("Alert pop-uo doesn't exist");
        }
    }

    protected void acceptAlertPopUp() {
        String alertString = "";
        try {
            Alert alert =getDriver().switchTo().alert();
            alertString = alert.getText();
            alert.accept();
            pageLogger.info(alertString + " - Alert text");
        } catch (NoAlertPresentException e) {
            pageLogger.info("Alert pop-uo doesn't exist");
        }
    }

    protected void executeJSCommand(String command) {
        JavascriptExecutor javascript = (JavascriptExecutor) getDriver();
        javascript.executeScript(command);
    }

    protected void executeJSCommand(String command,WebElement element) {

        pageLogger.info("Executing command [ "+command+" ] with JS");
        JavascriptExecutor javascript = (JavascriptExecutor) getDriver();
        javascript.executeScript(command, element);
    }

    protected void swipeElementHorizontally(WebElement el){
        int width = getDriver().manage().window().getSize().getWidth();
        System.out.println(width);
        int xOrd=el.getLocation().getX();
        int yOrd=el.getLocation().getY();

        //Swipe from Right to Left.
        CommonManager.waitSeconds(1);
        //Swipe from Left to Right.
        getDriver().swipe(xOrd, yOrd, width - xOrd, yOrd, 1000);
    }

    protected void swipeElementAction(WebElement el){
        TouchAction action = new TouchAction(getDriver());
        int width = getDriver().manage().window().getSize().getWidth();
        System.out.println(width);
        int xOrd=el.getLocation().getX();
        int yOrd=el.getLocation().getY();

        //Swipe from Right to Left.
        CommonManager.waitSeconds(1);

        action.press(xOrd, yOrd).waitAction(2000).moveTo(width-xOrd-1, yOrd).release().perform();
    }

    protected void swipeWithJS(WebElement el){
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        int width = getDriver().manage().window().getSize().getWidth();
        System.out.println(width);
        int xOrd=el.getLocation().getX();
        int yOrd=el.getLocation().getY();
        HashMap<String, Integer> swipeObject = new HashMap<String, Integer>();
        swipeObject.put("startX", xOrd);
        swipeObject.put("startY", yOrd);
        swipeObject.put("endX", width-xOrd-1);
        swipeObject.put("endY", yOrd);
        swipeObject.put("duration", 1);
        js.executeScript("mobile: swipe", swipeObject);
    }

    protected void swipeToElement(WebElement startEl,WebElement destEl){

        pageLogger.info("Swiping element");
        TouchAction action = new TouchAction(getDriver());
        int xOrd=startEl.getLocation().getX();
        int yOrd=startEl.getLocation().getY();
        int destX=destEl.getLocation().getX();
        if((destX-xOrd)<90){
            destX=1 ;
            xOrd=startEl.getSize().getWidth();;
        }
        //Swipe from Left to Right.
        CommonManager.waitSeconds(1);
        action.press(xOrd, yOrd).moveTo(destX, yOrd).release().perform();

    }


    protected void swipeToElementExp(WebElement slideEl,String side){
        pageLogger.info("Swiping element to "+side);
        int width=getDriver().manage().window().getSize().getWidth();
        int leftEdge= (int) (width*0.015);
        int rightEdge= (int) (width*0.85);
        int xOrd=slideEl.getLocation().getX();
        int yOrd=slideEl.getLocation().getY();
        if(side.equalsIgnoreCase("left")){
            getDriver().swipe(slideEl.getSize().getWidth() - xOrd, yOrd, leftEdge, yOrd, 1000);
        }else{
            getDriver().swipe(xOrd, yOrd, rightEdge, yOrd, 1000);
        }
        //Swipe from Left to Right.
        CommonManager.waitSeconds(1);

    }

    protected void touchTillContextEvent(WebElement el){
        pageLogger.info("Executing long press");
        TouchAction action = new TouchAction(getDriver());
        action.longPress(el,3000).release().perform();
    }

    protected void tapElement(WebElement el){
        TouchAction action = new TouchAction(getDriver());
        int  xOrd=el.getLocation().getX();
        int  yOrd=el.getLocation().getY();
        action.tap(el).perform();
    }

    protected void  switchToNative(){
        getDriver().execute(DriverCommand.SWITCH_TO_CONTEXT, ImmutableMap.of("name", "NATIVE_APP"));
    }

}

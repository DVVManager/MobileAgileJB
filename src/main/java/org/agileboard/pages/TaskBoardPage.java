package org.agileboard.pages;

import org.agileboard.appdriver.DriverProvider;
import org.agileboard.common.CommonManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;


import static org.agileboard.pages.Locators.BoardHeaderLocators.*;
import static org.agileboard.pages.Locators.StagePagesLocators.*;

/**
 * Created by Administrator on 1/29/2017.
 */
public class TaskBoardPage extends BasePage {

    protected Logger logger = Logger.getLogger(TaskBoardPage.class);


    public TaskBoardPage(DriverProvider driverProvider) {
        super(driverProvider);
        PageFactory.initElements(getDriver(),this);
    }

    public void raiseNewTaskCreation(){
        logger.info("Raising new task creation");
        CommonManager.waitSeconds((int) 0.5);
        clickIfVisible(getElementBy(By.xpath(ADD_TASK_BUTTON)));
        CommonManager.waitSeconds((int) 1.5);
    }

    public void navigateToDoStage(){
        logger.info("Navigating on 'TO-DO' stage");
        clickIfVisible(getElementBy(By.xpath(TO_DO_LINK)));
    }

    public void navigateDoingStage(){
        logger.info("Navigating 'DOING' stage");
        clickIfVisible(getElementBy(By.xpath(DOING_LINK)));
    }

    public void navigateDoneStage(){
        logger.info("Navigating 'DONE' stage");
        clickIfVisible(getElementBy(By.xpath(DONE_LINK)));
    }

    public void moveTaskToState(String taskName,String stateColumn){
        logger.info("Move task "+taskName+" to state: "+stateColumn);
        WebElement destElement=null;
        switch(stateColumn){
            case "Do": destElement=getElementBy(By.xpath(TO_DO_LINK)); break;
            case "Progress": destElement=getElementBy(By.xpath(DOING_LINK)); break;
            case "Done": destElement=getElementBy(By.xpath(TO_DO_LINK)); break;
        }
        swipeToElement(getElementBy(By.xpath(String.format(TASK_BY_NAME, taskName))), destElement);
    }

    public void moveToPrevOrNextState(String taskName,String direction){
        logger.info("Moving task "+taskName+" to direction: "+direction);
        swipeToElementExp(getElementBy(By.xpath(String.format(TASK_BY_NAME, taskName))), direction);
    }

    public void deleteTask(String taskName){
        logger.info("Deleting task "+taskName);
        CommonManager.waitSeconds(1);
        WebElement task=getElementBy(By.xpath(String.format(TASK_BY_NAME, taskName)));
        touchTillContextEvent(task);
        clickIfVisible(getElementBy(By.xpath(DELETE_POP_UP_BUTTON)));

    }

    public void assertTaskIsCreated(String name){
        pageLogger.info("Verifying task " + name + " to be created");
        Assert.assertTrue(webElementExists(getElementBy(By.xpath(String.format(TASK_BY_NAME, name)))));
    }

    public void assertTaskExists(String name,boolean positive) {
        pageLogger.info("Verifying task " + name + " exists");
        if(positive) {
            Assert.assertTrue(getElementsBy(By.xpath(String.format(TASK_BY_NAME, name))).size()>0,"Task was not found");
        }else{
            Assert.assertFalse((getElementsBy(By.xpath(String.format(TASK_BY_NAME, name)))).size()>0,"Task still exists");
        }
    }
}

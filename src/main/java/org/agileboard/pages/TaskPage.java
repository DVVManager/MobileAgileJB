package org.agileboard.pages;

import org.agileboard.appdriver.DriverProvider;
import org.agileboard.common.CommonManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import static org.agileboard.pages.Locators.TaskLocators.*;

/**
 * Created by Administrator on 1/29/2017.
 */
public class  TaskPage extends BasePage {


    public TaskPage() {
        PageFactory.initElements(getDriver(),this);
    }

    public void  fillNewTask(String taskName,String descr){
        fillNewTaskName(taskName);
        fillNewTaskDescr(descr);
    }

    public void fillNewTaskName(String taskName){

        WebElement taskNameEl=getDriver().findElementByXPath(TASK_NAME);
        clearAndType(taskNameEl, taskName);
    }

    public void fillNewTaskDescr(String descr){

        WebElement taskDescrEl=getDriver().findElementByXPath(DESCRIPTION);
        clearAndType(taskDescrEl,descr);
    }

    public void completeTaskCreation(){
        clickIfVisible(getElementBy(By.xpath(DONE_BUTTON)));
        CommonManager.waitSeconds(1);
    }
}

package org.agileboard.pages.Locators;

/**
 * Created by Administrator on 1/29/2017.
 */
public class BoardHeaderLocators {
    public static final String ADD_TASK_BUTTON="//android.widget.TextView[@content-desc='New Task']";
    public static final String TO_DO_LINK="//*[@class='android.app.ActionBar$Tab'][1]";//android.widget.TextView[@text='To-do']";
    public static final String DOING_LINK="//android.widget.TextView[@text='Doing']";
    public static final String DONE_LINK="//android.widget.TextView[@text='Done']";
    public static final String OPTIONS_BUTTON="//android.widget.ImageButton[@content-desc='More options']";
    public static final String TASK_IN_STAGE_COUNT="//android.widget.TextView[@text='%s']/following-sibling::android.widget.TextView";
    public static final String BACK_TO_PROJECTS="";
    public static final String CURRENT_PROJECT_NAME="//android.widget.LinearLayout/android.widget.TextView";
    public static final String PROJECT_CHECK="//android.widget.TextView[@text='%s']";
    public static final String TASK_BY_NAME="//android.widget.TextView[@text='%s']";

}

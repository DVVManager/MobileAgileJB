package org.agileboard.pages;

import org.agileboard.appdriver.DriverProvider;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Administrator on 1/29/2017.
 */
public class ProjectPage extends BasePage {

    public ProjectPage() {
        PageFactory.initElements(getDriver(),this);
    }
}

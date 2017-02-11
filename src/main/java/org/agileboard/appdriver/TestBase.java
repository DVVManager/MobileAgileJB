package org.agileboard.appdriver;

import org.jbehave.core.annotations.AfterScenario;
import org.jbehave.core.annotations.BeforeScenario;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Administrator on 2/5/2017.
 */
public class TestBase {



    @BeforeScenario
    public void initialization() {
        DriverProvider.init();
        System.out.println("START");
    }
    @AfterScenario
    public void cleanup(){
        DriverProvider.end();
    }
}

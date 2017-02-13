package org.agileboard.appdriver;

import org.jbehave.core.annotations.AfterScenario;
import org.jbehave.core.annotations.BeforeScenario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

/**
 * Created by Administrator on 2/5/2017.
 */
@ContextConfiguration(locations = {"classpath*:bean.xml"})
public class TestBase {
    @Autowired
    protected DriverProvider driverProvider;

    protected DriverProvider getProvider(){
        if(driverProvider==null){
            throw new RuntimeException("Provider was not injected - can't get DriverProvider");
        }
        return this.driverProvider;
    }

    @BeforeScenario
    public void initialization() {
        driverProvider.init();
    }
    @AfterScenario
    public void cleanup(){
        driverProvider.end();
    }
}

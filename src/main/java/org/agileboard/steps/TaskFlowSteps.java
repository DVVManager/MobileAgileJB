package org.agileboard.steps;

import org.agileboard.appdriver.TestBase;
import org.agileboard.pages.TaskBoardPage;
import org.agileboard.pages.TaskPage;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

/**
 * Created by Administrator on 1/30/2017.
 */
public class TaskFlowSteps extends TestBase {

    private TaskBoardPage taskBoardPage;
    private TaskPage taskPage;

    @Given("I click add button")
    public void raiseNewTask(){
        taskBoardPage =  new TaskBoardPage(driverProvider);
        taskBoardPage.raiseNewTaskCreation();
    }

    @When("I create task with $name and $description")
    public void createTask(@Named("name") String name,@Named("description") String description){
        taskPage=new TaskPage(driverProvider);
        taskPage.fillNewTask(name, description);
        taskPage.completeTaskCreation();
    }

    @Then("Task with $name is created")
    public void checkTaskIsCreated(@Named("name")String name){
        taskBoardPage.assertTaskIsCreated(name);
    }


}

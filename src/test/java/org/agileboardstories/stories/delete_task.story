Scenario: Delete task

Given I move name to right
When I check name is on progress
Given I move name to right
When I check name is on done
When I delete name
Then Task with name was deleted
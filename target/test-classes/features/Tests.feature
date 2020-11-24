Feature: Planit Assesment tests

@tc1
Scenario: Test case 1
Given user will launch Googlechrome browser
When user will open the application url "jupiterUrl"
Then user will switch from the home page go to contact page
And user click submit button
And Validate mandatory field error messages
And Populate mandatory fields
Then Validate errors are gone

@tc2
Scenario: Test case 2
Given user will launch Googlechrome browser
When  user will open the application url "jupiterUrl"
Then  user will switch from the home page go to contact page
And   Populate mandatory fields
And   user click submit button
Then  Validate successful submission message

@tc3
Scenario: Test case 3
Given user will launch Googlechrome browser
When  user will open the application url "jupiterUrl"
Then  user will switch from the home page go to contact page
And   Populate mandatory fields with invalid data
Then  Validate field level errors

@tc4
Scenario: Test case 4
Given user will launch Googlechrome browser
When  user will open the application url "jupiterUrl"
Then  user will switch from the home page go to shop page
And   Click buy button 2 times on Funny Cow item
And   Click buy button 1 time on Fluffy Bunny item
And   Click the cart menu
Then  Verify the items are in the cart


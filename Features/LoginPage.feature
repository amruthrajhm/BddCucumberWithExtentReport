@regressionTests
Feature: LoginPage

Scenario:  login with invalid user name and password
	Given User launch Chrome browser with given URL
	And user Enter username as "amruth@gmail.com" and Password 	as "testUser"
	Then Click Submit Btn
	Then UserShould Login Tilte Should be "Log in to Trello"

@smokeTests
Scenario:  login with invalid user name and password
	Given User launch Chrome browser with given URL
	And user Enter username as "amruth@gmail.com" and Password 	as "testUser"
	Then Click Submit Btn
	Then UserShould Login Tilte Should be "Logged in to Trello"

# Translink Bus Schedule LookUp Automation

This Project is about automating the user flow of searching for a bus stop schedule and add it as favourte route.



**Description**

This Project covers the automation of the below userflows.

1. Go to Bus Schedule page

2. Look up for “#99 – UBC B-Line” Schedule

3. Select Date, Start Time and End Time

4. Select the desired stop

5. Add the bus stop schedule to Favourites

Note: Framework used is **TestNG**



**Getting Started**


**Prerequisite**

Download the project to your local system. 

Open the project in Eclipse or any IDE.

Chrome driver installation is not required.

**About the Test Scenarios**

Below are the 4 Test Scenarios and their respective Test File names in Project

Test Scenario 1: Navigate to Bus Schedule page and verify it is loaded
  
  Steps 1 - 4 from Assignment sheet
  

Test Scenario 2: Go to “#99 – UBC B-Line" Schedule and Search for Bus stop that contains "#50913" with specifin Date and Time conditions
  
  Steps 5 - 8 from Assignment sheet
  

Test Scenario 3: Add "#50913" bus stop as Favourite and verify if it is added successfully
  
  Steps 9 - 11 from Assignment sheet
  

Test Scenario 4: Validate the first 4 timestamps are listed in Order and Time intervals between 2 buses is less than 60 minutes
  
  Bonus Point Test Scenario 1 -2 from assignment sheet


  

**To verify these run the below respective TestNG files**


Project Navigation: Translink_UI -> src/test/java -> Selenium_Framework.Translink_UI

Test Scenario 1 -  LoadBusPageTest

Test Scenario 2 -  SearchBusScheduleAndClickTest

Test Scenario 3 -  AddRouteAsFavouriteTest

Test Scenario 4 -  ValidateBusTimesTest









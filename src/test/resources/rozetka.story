Narrative:
As a user
I want to find the cheapest phones
In order to compare compare their prices

Scenario: save cheapest devices data into file
Given I open url "http://rozetka.com.ua/"
And I open smartphones section
And I open affordable smartphones section
And I sort affordable smartphones by price ascending
And I save data for 3 devices from 3 pages into file

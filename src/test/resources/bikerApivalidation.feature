Feature: To do api testing on the biker Api



  Scenario: Validate response code
    When I hit the biker Api
    Then I should get valid response code as 200


   Scenario: validate the country code for Frankfurt city is Germany
     When I hit the biker Api
     Then the country code for Frankfurt city should be DE, Germany


   Scenario: get the latitude and longitude for city "Frankfurt"
     When I hit the biker Api
     Then the latitude and longitude for Frankfurt should be retrieved

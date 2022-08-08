@yatra
Feature: yatra Flight Search
@yatra
Scenario: search the flight from bangalore to delhi for tomorrow
					When enter "BLR" and "DEL" in fromToSection
					Then enter departure date month as "August" and day "10/08/2022"
					Then select Travels  details "economy" and "adults" and "child" and "infant"
					Then select seat "type"
					Then Search Filghts


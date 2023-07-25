# iTunes Search API Test Framework

## Description

The Search API allows you to place search fields in your website to search for content within the iTunes Store, App Store, iBooks Store and Mac App Store.

This project allows to test Search API with all possible combination of query parameters as mentioned in requirements


### Execution

In order to execute the test cases, need to clone this repository:

  ```
  git clone https://github.com/debmalya92/iTunesSearchAPITest.git
  ```

Install Maven Library

  ```
  Run mvn test
  ```


### Framework Configuration

- Test case files added as per query parameters mentioned in requirement
- All possible negative and positive scenarios are mentioned in each respective test classes
- test configs class used as base class where necessary data providers are mentioned for each tests
- all test classes are added in testng.xml file
- endpoints class contains get api call and search endpoint
- resources folder contains test plan document where all scenarios are mentioned
- resources folder contains issues document where all discovered issues are logged


## Execution

- Total 20 test scenarios are covered to test the API and all are mentioned in test plan
- Total 46 test cases will be executed to test Search API
	- Passed 35 test cases and failed 11 with all test data
	- 11 test cases are failed due to issues mentioned in the issue document (in resources folder)

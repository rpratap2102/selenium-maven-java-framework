`selenium-maven-java-framework`
===================

This sample  is written in **[JAVA](https://www.java.com/en/)** and is a framework for writing selenium test automation **[Selenium4](https://www.selenium.dev/documentation/webdriver/getting_started/upgrade_to_selenium_4/)** and **[maven](https://gradle.org)**
Everything is set up and tests can be added straight away.
Used Testrunner is **[TestNG](https://testng.org/doc/)**.
For reporting **log4j** is used and **[extent report](https://www.extentreports.com/docs/versions/5/java/index.html)** is used for HTML reports 
To execute the tests just browse to the path where the `pom.xml` is located via terminal and type `mvn clean install` or execute the tests in your IDE using TestNG plugins.
The Project will use Chrome Browser in Headed mode by default / if no other browser is stated in `\src\test\resources\config\BaseConfig.properties` 
(see list of implemented browsers for more info on how to use them).

----
# `The configuration of the framework` #

### Test config ### 
Property file located at `\src\test\resources\config\BaseConfig.properties`  have following parameters

1. **app.url** `Base URL of the testing site`
2. **app.driver.waitSeconds**= `Default wait time`
3. **app.driver.browser**= `Default browser` Supported browsers : [chrome](https://www.google.com/intl/en_in/chrome/), [firefox](https://www.mozilla.org/en-US/firefox/new/), [edge](https://www.microsoft.com/en-us/edge), [safari](https://www.apple.com/in/safari/)

### Framework config ###
Constants used throughout the framework are listed in the java class at `\src\main\java\com\frameworks\selenium\utils\Constants.java`


### Selenium Wrapper ###

Not all selenium methods are covered by the framework but can be easily added to the class `com.frameworks.selenium.core.SeleniumWrapper` and can be accessed through driver instance.

---- 
# `How to run tests` #

### Eclipse ###


1. Import the project in Eclipse.
2. Right-click on any `.xml file with prefix testng`.
3. Select Run As TestNG test.
4. Tests defined in the selected .xml file will start running.


### Command Line ###


1. Navigate to where pom.xml is in the command line.
2. run the command `mvn clean install` to run default testng.xml
3. To run a specific TestNG file run `mvn clean install -DsuiteXmlFile=testngfile.xml]`


---- 
# `Features of framework`  #

1. `Page Object Model` Framework.
2. `TestNG` is used as TestRunner.
3. Data Provider to read `test date from .xlxs file`.
4. `Maven` as build tool.
5. Usage of `implicit and explicit waits`.
6. org.aeonbits.owner config file manager to read `test data config file for` browser name site URL and wait.
7. Configurable to run on different `browsers chrome, safari, firefox, edge`.
8. `Screenshot in case of test failure` as base64image in extent report.
9. `Extent report for HTML report` with all steps performed using driver and screenshot on failure.
10. `log4j for logging` every step performed.
11. `Command-line runnable` tests.
12. `Easy to add capabilities`.
13. `Little to no changes for reporting`.
14. `Archive of reports` keeps a record of all old executions.

----
# `Data provider structure` #
Data is provided from `amazonTestData.xlxs` file located at `\src\test\resources\testData\`

Structure:
Every sheet is the named after test class name and the first column is reserved for the test method name.
`Note:` Data is case sensitive.
 
----
# `Reporting and logging` #

For the `logging` latest stable version `log4j2` at the time is used and it can be configured by changing the `log4j2.xml` file located at `\src\main\resources`

For reporting latest stable version of `extent report` at the time is used. its name and title can be configured from `Constants.java` file in the framework.

The reporting is at the `level of selenium operations` and grouped under the method name. It is `Thread-safe` and it can be generated even if the execution is `parallel at the method level`. `base64`  conversion of the screenshot is used so no need to share the screenshot folder if in case of HTML report needs to be shared.

`createNewNode` method is required to use as first line of every method in page object to create the grouping on report if its not used by default all logs will be visible under testmethod name.

---- 
# `Test Cases` #

Testcases are distributed on 4 different pages using the Data provider in total tests are `17 tests`.
Testcase list written for amazon.in:
Pages
- CartPageTests
    1. addItemToCart                (params: product)
        - oneplus 10 pro
    2. removeItemfromCart           (params: product)
        - oneplus 10 pro
    3. updateItemQuantity           (params: product)
        - bean bag
- FilterPageTests
    1. priceRangeFilterTestOnMobile (params: minPrice, maxPrice)
        - 1000,	2000
        - 2000,	30000
- HomePageTests
    1. assertPageTitle
    2. switchLanguageTest           (params: language)
        - hi_IN
        - mr_IN
        - ta_IN
        - te_IN
        - kn_IN
        - ml_IN
        - bn_IN
    3. contactUsRedirectionTests    (params: socialMedia)
        - Facebook
        - Instagram
- SearchPageTests
    1. searchProducts  (seachText, assertTitle)
        - oneplus 10 pro,   OnePlus 10 Pro 5G - Capture Every Horizon
        - iphone 13,        iPhone 13. Superbright. Supercolorful. Supersharp.


----
# `The architecture of the Project` #

Folder Structure
```
📦selenium
 ┣ 📂Archive_test_results				- Folder to store archived extent reports.
 ┣ 📂logs
 ┃ ┣ 📜Error.log						- Error logs of the executions.
 ┃ ┗ 📜Info.log							- All logs of the executions.
 ┣ 📂current_test_results
 ┃ ┗ 📂 execution time
 ┃ ┃ ┗ 📂screenshot						- Folder to save failed screenshot
 ┃ ┃ ┗ 📜index.html						- Extent report with scrrenshot attached(in case of failure).
 ┣ 📦src.main.java.com.frameworks.selenium
 ┃ ┣ 📂core												
 ┃ ┃ ┣ 📂locators										
 ┃ ┃ ┃ ┣ 📜WebElementLocator.java		- Wrapper to create web locators that can be dynamically updated and have more details.
 ┃ ┃ ┃ ┗ 📜WebLocatorType.java			- Different types of web locator supported.
 ┃ ┃ ┣ 📜BasePage.java					- Base Page to be extended by all pages to impliment reporting till method level
 ┃ ┃ ┣ 📜SeleniumWrapper.java			- Wrapper on selenium operations to impliment logging and reporting to lowest level.
 ┃ ┃ ┗ 📜WebDriverHandler.java			- Utility to create selenium webdrivers for the given browser(To run test at method level).
 ┃ ┃ ┣ 📂exceptions
 ┃ ┃ ┃ ┣ 📜FrameworkException.java		- Custom exception to handle general runtime throws. 
 ┃ ┃ ┃ ┗ 📜UnsportedException.java		- Custom exception to handle runtime throws regarding unsuported operations.
 ┃ ┃ ┣ 📂reporting
 ┃ ┃ ┃ ┣ 📜ExtentReport.java			- Class to create the instance of ectent report.
 ┃ ┃ ┃ ┣ 📜ExtentReportLogger.java		- Class to exposs different operations thet can be performend on extent report.
 ┃ ┃ ┃ ┗ 📜ReportListner.java			- Listner to create report instance and tests in extent report automatically for test execution.
 ┃ ┃ ┗ 📂utils
 ┃ ┃   ┣ 📜Constants.java				- Constants used throughout the framework. 
 ┃ ┃   ┣ 📜ExcelReader.java				- Utlity to read data from given excell.
 ┃ ┃   ┣ 📜ReportUtil.java				- Utlity to archive/delete old reports.
 ┃ ┃   ┣ 📜ScreenShot.java				- Utility to capture screeenshot.
 ┃ ┃   ┣ 📜Sleeper.java					- Utility to add sleep in case implicit and explicit waits are not aplicable.
 ┃ ┃   ┗ 📜StringUtils.java				- Utility to perform operations on string.
 ┃ ┗ 📦src.main.resources
 ┃   ┗ 📜log4j2.xml						- LOG4J2 config file.
 ┣ 📦src.test.java.com.frameworks.selenium
 ┃ ┣ 📂base
 ┃ ┃ ┗ 📜TestBase.java					- Test Base to setup the driver and add close the browser adter test.
 ┃ ┣ 📂config
 ┃ ┃ ┗ 📜BaseConfig.java				- aeonbots config file reader.
 ┃ ┣ 📂data
 ┃ ┃ ┗ 📂provider
 ┃ ┃   ┗ 📜AmazonTestDataProvider.java	- Test Data provider for the file amazonTestData.xlxs
 ┃ ┣ 📂pages
 ┃ ┃ ┣ 📜CartPage.java					- Page object for Cart Page on the test site, locators an operatios peroformed on the page 
 ┃ ┃ ┣ 📜FilterPage.java				- Page object for Filter Page on the test site, locators an operatios peroformed on the page
 ┃ ┃ ┣ 📜HomePage.java					- Page object for Home Page on the test site, locators an operatios peroformed on the page
 ┃ ┃ ┣ 📜ProductPage.java				- Page object for Product Page on the test site, locators an operatios peroformed on the page
 ┃ ┃ ┗ 📜SearchPage.java				- Page object for Search Page on the test site, locators an operatios peroformed on the page
 ┃ ┗ 📂tests
 ┃   ┣ 📜CartPageTests.java				- Tests for the Cart Page
 ┃   ┣ 📜FilterPageTests.java			- Tests for the Filter Page
 ┃   ┣ 📜HomePageTests.java				- Tests for the Home Page
 ┃   ┗ 📜SearchPageTests.java			- Tests for the Search Page
 ┣ 📦src.testresources
 ┃ ┣ 📂config
 ┃ ┃ ┗ 📜BaseConfig.properties			- Config file for the tests
 ┃ ┗ 📂testData
 ┃   ┗ 📜amazonTestData.xlsx			- Test Data file for tests on amazon.in
 ┣ 📜pom.xml							- Maven pom.xml to add dependencies and plugins.
 ┣ 📜README.md							- ReadMe file for the project
 ┣ 📜testng.xml							- Default testng file
 ┣ 📜testng_parallel_all.xml			- Testng file for all pages with 2 parallel thread count on classes
 ┣ 📜testng_smoke.xml					- Testng file for all test with group name Smoke.
```

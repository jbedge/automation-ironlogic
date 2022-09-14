## Running via the command line (with examples)

:**Important Commands:**
taskkill /F /IM chromedriver.exe
setTimeout(function(){debugger;},5000)


**Required fields:**

*suiteFile* - which suite file to run


**Optional fields:**

*browser* - which browser to run on (will default to Chrome if not specified)

*configFile* - which config file to load. Note that this value can be specified in the suite XML file. If specified in both command line and the suite file, the command line value will take precedence. If none specified, will default to DeTestConfig.xml.


**Samples:**


*Basic command:*

mvn clean test -DsuiteFile=mySuiteFile.xml 

*With a browser:*

mvn clean test -DsuiteFile=mySuiteFile.xml -Dbrowser=chrome

*With a config file:*

mvn clean test -DsuiteFile=mySuiteFile.xml -DconfigFile=DeTestConfig.xml


*With everything:*

mvn clean test -DsuiteFile=mySuiteFile.xml -DconfigFile=DeProdConfig.xml  -Dbrowser=chrome



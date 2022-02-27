# ProjectBryan

General Use

Import the repository to your own eclipse workspace.
Run the project on tomcat server.
Open up a web browser to access the localhost.

Jenkins

If you are unable to build the project, feel free to create a new branch and delete the test files first. After that build the server.
Add back the deleted test files after building your server as the testing files require the Tomcat server to be running first.

Tomcat Server

Make sure your Tomcat server is running in the background when jenkins is building the project from git repository.

Selenium Test

Tomcat server Must be running.
Right click on SeleniumTest file and run as TestNG to test the content of web application running on Tomcat server.

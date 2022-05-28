# tsac

### Sample Spring Boot app that showcases software testing

The main idea behind the app is to show the different types
of software testing and how important are them. The main goal
was to implement any useful application and then to add the 
necessary tests (mainly unit tests).

In the meantime, the app was transformed so that now it's 
using the following technologies:
 - No more JUnit! Everything is now tested with Cucumber in a BDD way
 - GitHub Actions for CI (and a bit of CD), triggered for pushes on main and develop:
   - Build, then run cucumber tests with maven
   - Package application (artifact is generated for local testing)
   - SonarCloud for code analysis (https://sonarcloud.io/summary/new_code?id=PirvuCatalin_tsac)
   - Heroku GitHub Action for automatically deploy at https://tsac-app.herokuapp.com
   - Integration and Performance tests with jmeter that runs on the above application 
   (results are also available for download as an artifcat)

Example of a workflow run at: https://github.com/PirvuCatalin/tsac/actions/runs/2400636587.
<br>
There you'll also find the Package artifact (which you can then use for running the app 
locally `java -jar tsac-0.0.1-SNAPSHOT.jar` and oppening a browser at `localhost:8080`).
<br>
Files `jmeter-integration-test-results` and `jmeter-performance-test-results` contain the test results. 
The Apache JMeter Dashboard is available after oppening the `index.html` file.

### The name

The name `tsac` stands for "Testare Software și Asigurarea Calității", which is the romanian
translation for Software Testing and Quality Assurance, basically the name of a course I'm 
taking at the Faculty of Automatic Control and Computer Science (Politehnica University of Bucharest),
Information Management and Protection (MPI) master.

This is basically the semester project for *TSAC* course.


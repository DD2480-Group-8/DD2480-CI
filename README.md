# DD2480-CI
### Assigment 2 of DD2480
The goal of this assignment is to master the core of continuous integration. To achieve this goal, we implemented a small continuous integration CI server. This CI server will only contain the core features of continuous integration. 

The CI server supports compiling the group project, executing the automated tests of the group project, and supports notification of CI results.

---------
## Building and running
The program is built using [**Maven**](https://maven.apache.org), a build tool
that downloads the necessary dependencies to run the project. It can also run the test suite to verify that
the functionality of the program is intact.

The project uses Java 11.

Since this project uses many dependencies, the maven-assembly-plugin has been added to package
all these dependencies together with the source code into a single .jar file.

### Running using CLI:
1. `mvn package`in the root directory of the repository
2. `java -cp target/DD2480-CI-1.0-SNAPSHOT-jar-with-dependencies.jar group8.server.ContinuousIntegrationServer`

### Running via IntelliJ:
1. Open repository in IntelliJ
2. Run the ContinuousIntegrationServer class to start listening for requests on port 8080.


To enable the Continuous Integration Server to notify GitHub regarding the result of the integration tests, a
Personal Access Token to the GitHUb API is needed.  
In `src/main/java/group8/Config.java`, add this token as the `Public Static String GitToken`. 
This field is a Personal Access Token and should _not_ be checked into the repository. If you do not know how to get one, click 
[here](https://docs.github.com/en/authentication/keeping-your-account-and-data-secure/creating-a-personal-access-token) 
to read a tutorial. Important to know is that you need write permission to the repo in question to be able to perform this action,
and the token needs, at least, the repo:status scope. 

## Essence
Our team has achieved most of the statements in the Essence Checklist for Team. Our team has achieved all points of the "Seeded" stage, as before emabarking on the assignment we have defined our intended outcomes and discussed our responsibilities, as well as make sure everyone is on the same page in terms of how we will proceed with the project. Our team also passes the "Formed" stage as we understand our individual responsibilities. While some members might not fully understand how to perform their work, our group has discussed and decided that we will take up roles that align with our competencies and if not, will reach out to the other group members for help. This leads to the "Collaborating" stage where we feel the matter of "working as one cohesive unit" is still in progress, given that some members might still need help from others with their tasks. Nonetheless, we communicate openly and honestly with each other and would progress on further once we are able to work cohesively. Parts of the "Performing" stage are also a work in progress as there are instances where reworking and backtracking is necessary and we have yet to completely eliminate wasted work. Finally, we have not reached the "Ajourned" stage as the project is still ongoing.  

## Statement of Contributions
**Emil:** handling incoming POST request on push, cloning of specific branch, compiling, testing and storing of builds, combining different parts of the project, general unit tests

**Simon:** handling API calls from application to Github, testing of code, reviewed PRs, minor contribution to README

**Viktor:** 

**Joakim:** Initialization of project, cloning of a specific repository, refactoring, javadoc descriptions and generation 

**Jovan:** README, refactoring

#SoftTech Project

1. Clone the project to your local directory by invoking `git clone https://github.com/GeeksException/softtech.git`
2. Open eclipse and import the project as Maven project
3. The project can be run using embedded Tomcat 7 (explained below) or it can also be run on an installed container in eclipse.
4. If you will be running the project on an installed container in eclipse, make sure that the project's environment profile (defined by Spring's system property `spring.profiles.active`) is defined as `development` or `production`. 
5. If you will be running the project using the embedded tomcat container, it is already defined under the `<systemProperties/>` of `tomcat7-maven-plugin` and is initially configured to run in `development`.
6. The main difference of `development` and `production` environments is that the development environment is using an embedded HSQL database and the production environment is using the database source defined in the jdbc.properties inside `./src/main/resources`.
7. Please note that the `development` environment is set to generate the DDL for the embedded HSQL database. Once the DDL is generated, Hibernate will look in the classpath for a file named `import.sql` and execute the scripts inside it. The `import.sql` of this project is located at `./src/main/resources`.

##Running the project using the embedded Tomcat container
1. Make sure you already have the latest code from the GitHub repository and already imported the project in Eclipse.
2. Right-click `pom.xml` > Run as > Maven build..
3. In the Main tab, click `Browse Workspace...` > choose softtech then click ok
4. Type in `package tomcat7:run` in Goals
5. In Source tab, click Add... > Select Java Project then click Ok > Select softtect then click Ok  
6. Click Apply then Run.
7. Wait for Tomcat to finish starting and then hit `http://localhost:8080`
8. Grab a beer and enjoy!

##Deploying the project to Openshift

For maven projects, Openshift expects a Maven profile named `openshift` to be present in pom.xml. Openshift also expects that the war file is named `ROOT.war` and placed in the directory specified in Jenkins.

Go to [Jenkins](https://jenkins-geng.rhcloud.com) and login using the username `admin` and password that starts with **Capital 'B'**

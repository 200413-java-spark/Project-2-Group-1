# Apache Spark Life Expectancy Analysis 1.0.1

******

Processes the data life expectancy data from U.S. Life Expectancy at Birth by State and Census Tract - 2010-2015

https://data.cdc.gov/NCHS/U-S-Life-Expectancy-at-Birth-by-State-and-Census-T/5h56-n989. 

The application computes the life expectancy by state and outputs them in the form of a d3 chart.

******
## Build
### Java
```
mvn clean package
java - jar project-1-john-1.0.1-SNAPSHOT.war
```

### DockerFile
```
FROM postgres
ENV POSTGRES_USER mydb
ENV POSTGRES_PASSWORD mydb
EXPOSE 5432
```

### Docker
```
docker build -t mydb -f ./Dockerfile
docker run --name mydb -d --rm -p 5432:5432 mydb
```

## Usage
```
java -jar spark.jar
java -jar output.jar <args> -> folder names
java -jar web.jar
```
## Design

### Main Algorithm: 
After the application is complied into Java jars. Then the spark jar must be submitted to an Amazon EMR cluster. 
From there the application will process the life expectancy data from a csv located within a s3 bucket.
The application will store the output to a s3 bucket. The user must start the output jar with the folder or folders of the spark output.
The application will retrieve the output from the s3 bucket and persist it to a PostgreSQL instance.
The user must start the web jar from which the jar will create a web view of the project website.
The result is stored under the dropdown of State Averages.


### Architecture:
**Business Logic Layer:**
- Java
- Apache Spark
- Spring Boot
- Spring MVC

**Data Access Layer:**
- PostgreSQL
- Spring JPA
- JDBC

**Presentation Layer:**
- D3
- Bootstrap 4
- JavaScript
- CSS
- HTML

**Build Tools:**
- Maven
- Docker
- JUnit
- Tomcat
- AWS - EC2
- AWS - EMR

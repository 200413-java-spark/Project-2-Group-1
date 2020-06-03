# Apache Spark Life Expectancy Analysis 1.0.2

******

***The Application Processes The Life Expectancy Data From "U.s. Life Expectancy At Birth By State And Census Tract - 2010-2015" Dataset.***

https://data.cdc.gov/NCHS/U-S-Life-Expectancy-at-Birth-by-State-and-Census-T/5h56-n989. 

***The Application Computes The Life Expectancy Averages By State Then Outputs Them In The Form Of A D3 Chart.***

******
## Build
### Java
```
mvn clean package
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
spark-submit spark.jar
java -jar output.jar <args> -> folder names
java -jar web.jar
```
## Design

### Main Algorithm: 
- The Spark Jar Must Be Submitted To An Amazon Emr Cluster Using Spark-submit. 
- The Application Will Process The Life Expectancy Data From A Csv Located Within A S3 Bucket.
- The Application Will Store The Output To A S3 Bucket. 
- User Must Start The Output Jar With The Folder Or Folders Of The Spark Output.
- The Application Will Then Retrieve The Output From The S3 Bucket And Persist It To A Postgresql Instance.
- User Must Start The Web Jar From Which The Jar Will Create A Web View Of The Project Website.
- The Drop Down Labeled State Averages Displays The Results.

### Architecture:
**Business Layer:**
- Java
- Apache SparkSQL

**Data Layer:**
- PostgreSQL
- Spring JPA
- JDBC

**Presentation Layer:**
- Spring Boot
- Spring MVC
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

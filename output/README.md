# Output Module
This application is a batch job that extracts a file from an AWS S3 bucket, transforms the data into a relational format, and then persists the transformed data to an SQL database.

## Build
#### Package Maven Project:
    mvn clean package

## Usage
#### Build & Execute:
    mvn clean package
    java -jar target/output-0.0.1-SNAPSHOT.jar
#### Build & Execute (format reference):
    mvn clean package
    java -jar target/*.jar arg1 arg2 [arg3/path/to/file.csv]
#### Build & Execute (shortcut):
    ./run-app.sh

## Tests
### Unit Tests
#### Run JUnit tests (format reference):
    mvn clean test -Dtest=TestClassName#testOne+testTwo
#### Test utility classes:
    mvn clean test -Dtest=AppPropertiesTest
    mvn clean test -Dtest=DaoUtilTest
#### Test Direct Access Objects (DAO):
    mvn clean test -Dtest=StateDaoTest

## Database
#### Connect:
    psql --host=3.17.207.114 --port=5432 --username=mydb --password --dbname=mydb
    psql postgresql://3.17.207.114:5432/mydb mydb
#### Build Schema:
    psql -h 3.17.207.114 -p 5432 -d mydb -U mydb -a -q -f src/main/resources/schema.sql
#### Show Schema:
    psql -h 3.17.207.114 -p 5432 -d mydb -U mydb -a -q -f src/main/resources/display.sql

## More
#### View Spark results:
>localhost:4040
>localhost:4040/jobs

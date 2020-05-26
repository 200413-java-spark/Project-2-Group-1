package spark.sparkSQL;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import com.github.sparkBusiness.sparkSQL.SutterSQL;

@Ignore
public class SutterSQLTest {

    // mvn clean test -Dtest=SutterSQLTest

    private static final String INPUT_FILE = "src/test/resources/input/life-expectancy-data.csv";
    private static final String INPUT_FILE_TEST = "src/test/resources/input/life-expectancy-data-sample.csv";
    private static SparkSession sparkSession;
    private static Dataset<Row> dataset;
    private static SutterSQL sutterSQL;

    @BeforeClass
    public static void createSession() {
        sparkSession = SparkSession.builder().appName("spark-sql-sutter").master("local[*]").getOrCreate();
        dataset = sparkSession.read().option("header", true).csv(INPUT_FILE);
    }

    @AfterClass
    public static void closeSession() {
        sparkSession.close();
    }

    @Test
    public void testSpark() {
        boolean completed = false;
        sutterSQL = new SutterSQL(dataset, sparkSession);
        try {
            sutterSQL.execute();
            completed = true;
        } catch (Exception e) {
            e.printStackTrace();
            completed = false;
        }
        Assert.assertTrue(completed);
    }

}
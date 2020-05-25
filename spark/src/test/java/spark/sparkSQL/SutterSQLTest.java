package spark.sparkSQL;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import com.github.sparkBusiness.sparkSQL.SutterSQL;

public class SutterSQLTest {
    
    // mvn test SutterSQLTest

    private static SparkSession sparkSession;
    private static Dataset<Row> dataset;
    private static SutterSQL sutterSQL;

    @BeforeClass
    public static void createSession() {

        // sparkSession = SparkSession
        //     .builder()
        //     .appName("spark-job")
        //     .master("local[*]")
        //     .getOrCreate();

        // dataset = sparkSession
        //     .read()
        //     .option("header", true)
        //     .csv("spark/life.csv");
        
        // sutterSQL = new SutterSQL(dataset, sparkSession);

    }

    @Test
    public void testSpark() {
        //sutterSQL.run();
        Assert.assertTrue(true);
    }

}
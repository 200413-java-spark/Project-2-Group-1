package com.github.sparkBusiness.sparkSQL;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import static org.apache.spark.sql.functions.col;

import com.github.sparkBusiness.util.SaveDataset;

public class SutterSQL implements SQLInt {

    public Dataset<Row> dataset;
    public SparkSession sparkSession;
    public final SaveDataset saveDataset;

    public SutterSQL(Dataset<Row> dataset, SparkSession sparkSession) {
        this.dataset = dataset;
        this.sparkSession = sparkSession;
        this.saveDataset = new SaveDataset();
    }

    @Override
    public void run() {
        try {
            // execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void execute() throws Exception {

        // learnSparkSQL();
        // return;

        // calculate max for each county
        Dataset<Row> df = dataset;
        Dataset<Row> result = df.drop(col("Census_Tract_Number"))
                .drop(col("Life_Expectancy_Range"))
                .drop(col("Life_Expectancy_Standard_Error"))
                .filter(col("State").isNotNull())
                .filter(col("County").isNotNull())
                .filter(col("Life_Expectancy").isNotNull())
                .withColumn("state", col("State"))
                .withColumn("county", col("County"))
                .withColumn("life_expectancy", col("Life_Expectancy").cast("decimal"))
                .groupBy(col("county"))
                .max("life_expectancy");
        
        // save Dataset as CSV file
        String[] columns = {"county", "state_abbreviation", "life_expectancy"};
        saveDataset.save(result, columns, 3, "output-files/county_max_2020-05-26.csv");

    }

    public void learnSparkSQL() {

        // Dataset<Row> df = dataset.cache();
        //Dataset<Row> df = dataset;

        // PRINT SCHEMA:
        //df.printSchema();
        
        // PRINT DATA
        //df.show();

        // METHOD: select
        //df.select("State").show();
        //df.select(col("County"), col("Life_Expectancy")).show(10);

        // METHOD: filter
        //df.filter(col("Life_Expectancy").gt(80)).select(col("County"), col("Life_Expectancy")).show();

        // METHOD: groupBy
        //df.groupBy("State").count().show();
        //df.groupBy("County").count().show();





        // More...
        // dataset.createOrReplaceTempView("County");
        // dataset.select("County").show(10);
        // Dataset<Row> count = sparkSession.sql("SELECT COUNT(*) FROM County");
        // count.show();
        // Dataset<Row> results = sparkSession.sql("SELECT COUNT(*) FROM counties");

    }

}
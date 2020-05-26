package com.github.sparkBusiness.sparkSQL;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import static org.apache.spark.sql.functions.col;

public class SutterSQL implements SQLInt {

    public Dataset<Row> dataset;
    public SparkSession sparkSession;

    public SutterSQL(Dataset<Row> dataset, SparkSession sparkSession) {
        this.dataset = dataset;
        this.sparkSession = sparkSession;
    }

    @Override
    public void run() {
        try {
            execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void execute() throws Exception {

        // Dataset<Row> df = dataset.cache();
        Dataset<Row> df = dataset;

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

        //Seq<String> columns = new Seq<String>();
        //columns.addString("county");
        //df.withColumns(colNames, cols);
        //df.withColumn("life_expectancy", col("Life_Expectancy").cast("decimal"))
            //.max()
        //df.apply("Life_Expectancy").cast("decimal");
            
        df.drop(col("State"))
            .drop(col("Census_Tract_Number"))
            .drop(col("Life_Expectancy_Range"))
            .drop(col("Life_Expectancy_Standard_Error"))
            .filter(col("Life_Expectancy").isNotNull());
            //.filter(col("Life_Expectancy").gt(0))
            //.filter(col("Life_Expectancy").lt(100))
            //.groupBy("County")
            // .max("Life_Expectancy")
            //.show();

        df.apply("Life_Expectancy").cast("decimal");

        df.groupBy("County")
            .max(col("Life_Expectancy").cast("decimal"))
            .show();




        // More...
        // dataset.createOrReplaceTempView("County");
        // dataset.select("County").show(10);
        // Dataset<Row> count = sparkSession.sql("SELECT COUNT(*) FROM County");
        // count.show();
        // Dataset<Row> results = sparkSession.sql("SELECT COUNT(*) FROM counties");
    
    }

}
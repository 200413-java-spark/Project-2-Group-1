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

        // Dataset<Row> data = dataset.cache();
        Dataset<Row> df = dataset;

        // PRINT SCHEMA:
        // df.printSchema();
        
        // PRINT DATA
        // df.show();

        // METHOD: select
        // df.select("State").show();
        df.select(col("County"), col("Life_Expectancy")).show(10);



        // More...
        // dataset.createOrReplaceTempView("County");
        // dataset.select("County").show(10);
        // Dataset<Row> count = sparkSession.sql("SELECT COUNT(*) FROM County");
        // count.show();
        // Dataset<Row> results = sparkSession.sql("SELECT COUNT(*) FROM counties");
    
    }

}
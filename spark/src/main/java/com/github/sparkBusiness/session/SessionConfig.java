package com.github.sparkBusiness.session;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

public class SessionConfig {

    private SparkSession sparkSession;
    private Dataset<Row> dataset;

    public SessionConfig(String arg) {

        if (arg.equals("local")) {
            localConfig();
        } else {
            EmrConfig();
        }

    }

    private void localConfig() {
         sparkSession = SparkSession
                .builder()
                .appName("spark-job")
                .master("local[*]")
                .getOrCreate();

        dataset = sparkSession
                .read()
                .option("header", true)
                .csv("spark/life.csv");

    }

    private void EmrConfig() {
        sparkSession = SparkSession
                .builder()
                .appName("spark-job")
                .getOrCreate();

        dataset = sparkSession
                .read()
                .option("header", true)
                .csv("s3://p2storage-jsd/life.csv");
    }

    public SparkSession getSparkSession() {
        return sparkSession;
    }

    public Dataset<Row> getDataset() {
        return dataset;
    }
}

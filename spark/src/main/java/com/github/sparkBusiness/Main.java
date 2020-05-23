package com.github.sparkBusiness;

import com.github.sparkBusiness.sparkSQL.MasterSQL;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

public class Main {
    public static void main(String[] args) {
//                LOCAL:
        SparkSession sparkSession = SparkSession
                .builder()
                .appName("spark-job")
                .master("local[*]")
                .getOrCreate();

        Dataset<Row> dataset = sparkSession
                .read()
                .option("header", true)
                .csv("spark/life.csv");
//                LOCAL:

//                EMR:
//                SparkSession sparkSession = SparkSession
//                        .builder()
//                        .appName("spark-job")
//                        .getOrCreate();
//
//                Dataset<Row> dataset = sparkSession
//                        .read()
//                        .option("header", true)
//                        .csv("s3://p2storage-jsd/life.csv");
//                EMR:


        MasterSQL masterSQL = new MasterSQL(dataset, sparkSession);

        masterSQL.runQ();


    }
}

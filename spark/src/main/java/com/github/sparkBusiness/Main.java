package com.github.sparkBusiness;

import com.github.sparkBusiness.sparkSQL.MasterSQL;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

public class Main {
    public static void main(String[] args) {

        SparkSession sparkSession = SparkSession
                .builder()
                .appName("spark-job")
                .master("local[*]")
                .config("spark.sql.warehouse.dir", "file:///c:/tmp/")
                .getOrCreate();

        Dataset<Row> dataset = sparkSession
                .read()
                .option("header", true)
                .csv("C:\\Users\\johnm\\IdeaProjects\\Project-2-Group-1-b\\spark\\guns-10.csv");

//        Dataset<Row> dataset = sparkSession
//                .read()
//                .option("header", true)
//                .csv("C:\\Users\\johnm\\IdeaProjects\\Project-2-Group-1\\web\\guns-10.csv");
//
//        Dataset<Row> dataset = sparkSession
//                .read()
//                .option("header", true)
//                .csv("C:\\Users\\johnm\\IdeaProjects\\Project-2-Group-1\\web\\guns-10.csv");


        MasterSQL masterSQL = new MasterSQL(dataset, sparkSession);

        masterSQL.runQ();

//        System.out.println("========== Print Data ==============");
//        dataset.show();
    }
}

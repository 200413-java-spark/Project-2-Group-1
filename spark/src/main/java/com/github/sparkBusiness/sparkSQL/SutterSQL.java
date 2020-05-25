package com.github.sparkBusiness.sparkSQL;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

public class SutterSQL implements SQLInt {
    
    public Dataset<Row> dataset;
    public SparkSession sparkSession;

    public SutterSQL(Dataset<Row> dataset, SparkSession sparkSession) {
        this.dataset = dataset;
        this.sparkSession = sparkSession;
    }

    @Override
    public void run() {

    }
}
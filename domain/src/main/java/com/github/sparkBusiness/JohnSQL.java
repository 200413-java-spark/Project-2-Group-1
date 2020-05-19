package com.github.sparkBusiness;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

public class JohnSQL implements SQLInt{

    public Dataset<Row> dataset;
    public  SparkSession sparkSession;

    public JohnSQL(Dataset<Row> dataset, SparkSession sparkSession) {
        this.dataset = dataset;
        this.sparkSession = sparkSession;
    }

    @Override
    public void run() {
        System.out.println("========== Print Data ==============");
        dataset.show();

    }
}

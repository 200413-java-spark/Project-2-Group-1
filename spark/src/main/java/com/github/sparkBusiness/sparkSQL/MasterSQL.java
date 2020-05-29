package com.github.sparkBusiness.sparkSQL;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

public class MasterSQL {

    public Dataset<Row> dataset;
    public SparkSession sparkSession;
    private final String mode;

    public MasterSQL(String mode, Dataset<Row> dataset, SparkSession sparkSession) {
        this.dataset = dataset;
        this.sparkSession = sparkSession;
        this.mode = mode;
        factory();
        sparkSession.close();
    }

    public void factory(){
        SQLInt sqlInt;
        switch (mode){
            case "john":{
                sqlInt = new JohnSQL(dataset, sparkSession);
                return;
            }
            case "daniel":{
                sqlInt = new DanielSQL(dataset, sparkSession);
                return;
            }
            case "sutter":{
                sqlInt = new SutterSQL(dataset, sparkSession);
            }
        }
    }
}

package com.github.sparkBusiness;

import com.github.sparkBusiness.session.SessionConfig;
import com.github.sparkBusiness.sparkSQL.MasterSQL;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

public class Main {
    public static void main(String[] args) {

//        Mode -> local || emr
        SessionConfig sessionConfig = new SessionConfig("local");
//        mode john || sutter || daniel
        new MasterSQL("john",sessionConfig.getDataset(), sessionConfig.getSparkSession());
    }
}



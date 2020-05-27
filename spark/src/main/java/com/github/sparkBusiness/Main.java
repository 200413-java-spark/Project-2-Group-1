package com.github.sparkBusiness;

import com.github.sparkBusiness.session.SessionConfig;
import com.github.sparkBusiness.sparkSQL.MasterSQL;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

public class Main {
    public static void main(String[] args) {

//        For Local Mode
//        SessionConfig sessionConfig = new SessionConfig("local");
//        For EMR
        SessionConfig sessionConfig = new SessionConfig("emr");
        MasterSQL masterSQL = new MasterSQL(sessionConfig.getDataset(), sessionConfig.getSparkSession());
        masterSQL.runQ();
    }
}



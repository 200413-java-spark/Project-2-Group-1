package com.github.sparkBusiness.sparkSQL;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

public class MasterSQL {

    public Dataset<Row> dataset;
    public SparkSession sparkSession;


    public MasterSQL(Dataset<Row> dataset, SparkSession sparkSession) {
        this.dataset = dataset;
        this.sparkSession = sparkSession;
    }

    public void runQ() {
        JohnSQL johnSQL = new JohnSQL(dataset, sparkSession);
        DanielSQL danielSQL = new DanielSQL(dataset, sparkSession);
        SutterSQL sutterSQL = new SutterSQL(dataset, sparkSession);

        johnSQL.run();
        danielSQL.run();
        sutterSQL.run();

        sparkSession.close();
    }
}

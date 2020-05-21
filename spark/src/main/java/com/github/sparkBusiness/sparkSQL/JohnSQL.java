package com.github.sparkBusiness.sparkSQL;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

public class JohnSQL implements SQLInt {

    public Dataset<Row> dataset;
    public SparkSession sparkSession;

    public JohnSQL(Dataset<Row> dataset, SparkSession sparkSession) {
        this.dataset = dataset;
        this.sparkSession = sparkSession;
    }

    @Override
    public void run() {

        dataset.createOrReplaceTempView("life");
        Dataset<Row> results = sparkSession.sql("select State, Life_Expectancy from life");
        Dataset<Row> results1 = sparkSession.sql("select County, Life_Expectancy from life");

        results.show();

        results1.show();
    }
}
// State,
// County,
// Life_Expectancy,
// Life_Expectancy_Range,
// Life_Expectancy_Standard_Error

// State, Life_Expectancy

// County, Life_Expectancy

//Alabama,"Autauga County, AL",0201.00,73.1,56.9-75.1,2.2348
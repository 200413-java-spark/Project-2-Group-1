package com.github.sparkBusiness.sparkSQL;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SaveMode;
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

        // State, Life Expectancy
        Dataset<Row> results = sparkSession
                .sql("select State, format_number(avg(Life_Expectancy), 2) as Average_Life_Expectancy from life group by State order by Average_Life_Expectancy desc");

results
        .coalesce(1)
        .write()
        .format("csv")
        .option("header", "true")
        .mode(SaveMode.Overwrite)
        .save("spark/src/main/resources/localdump");
//        .save("s3://p2storage-jsd/output/");


        // County, Life Expectancy
//        Dataset<Row> results1 = sparkSession
//                .sql("select County, format_number(avg(Life_Expectancy), 2) as Average_Life_Expectancy from life group by County order by Average_Life_Expectancy desc ");


//        results.show(10);
//        results1.show(10);
    }
}
// State,
// County,
// Life_Expectancy,
// Life_Expectancy_Range,
// Life_Expectancy_Standard_Error


//Alabama,"Autauga County, AL",0201.00,73.1,56.9-75.1,2.2348
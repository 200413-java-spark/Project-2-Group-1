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

sparkSession.conf().set("spark.sql.crossJoin.enabled", true);

        // State, Life Expectancy
        Dataset<Row> longestAverageByState = sparkSession
                .sql("select State, format_number(avg(Life_Expectancy), 2) as Average_Life_Expectancy from life group by State order by Average_Life_Expectancy desc");

        // County, Life Expectancy
//        Dataset<Row> longestAverageByCounty = sparkSession
//                .sql("select County, format_number(avg(Life_Expectancy), 2) as Average_Life_Expectancy from life group by County order by Average_Life_Expectancy desc ");


        // State, Life Expectancy
//        Dataset<Row> longestLifeRecord = sparkSession
//                .sql("select distinct State, Life_Expectancy  from life order by Life_Expectancy desc limit 1");

//        Dataset<Row> allTogether = longestAverageByCounty.join(longestAverageByCounty);



//        longestAverageByState.show();
//        longestAverageByCounty.show();
//        longestLifeRecord.show();

        longestAverageByState
                .coalesce(1)
                .write()
                .format("csv")
                .option("header", "true")
                .mode(SaveMode.Overwrite)
//                .save("spark/src/main/resources/localdump");
        .save("s3://p2storage-jsd/output");





    }
}
// State,
// County,
// Life_Expectancy,
// Life_Expectancy_Range,
// Life_Expectancy_Standard_Error


//Alabama,"Autauga County, AL",0201.00,73.1,56.9-75.1,2.2348
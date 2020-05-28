package com.github.sparkBusiness.sparkSQL;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SaveMode;
import org.apache.spark.sql.SparkSession;

public class JohnSQL implements SQLInt {

    public Dataset<Row> dataset;
    public SparkSession sparkSession;
    private Dataset<Row> longestAverageByState;
    private Dataset<Row> longestAverageByCountyTop5;
    private Dataset<Row> longestLifeRecord;
    private Dataset<Row> shortestLifeRecord;
    private Dataset<Row> removeNullsShortestLifeRecord;

    public JohnSQL(Dataset<Row> dataset, SparkSession sparkSession) {
        this.dataset = dataset;
        this.sparkSession = sparkSession;
    }

    @Override
    public void run() {

        dataset.createOrReplaceTempView("life");

        datasetBuilder();
        bucketBuilder(longestAverageByState, "output0");
        bucketBuilder(longestAverageByCountyTop5, "output1");
        bucketBuilder(longestLifeRecord, "output2");
        bucketBuilder(removeNullsShortestLifeRecord, "output3");

    }

    private void datasetBuilder() {
        longestAverageByState = sparkSession
                .sql("select State, format_number(avg(Life_Expectancy), 2) as Average_Life_Expectancy from life group by State order by Average_Life_Expectancy asc ");

        longestAverageByCountyTop5 = sparkSession
                .sql("select County, format_number(avg(Life_Expectancy), 2) as Average_Life_Expectancy from life group by County order by Average_Life_Expectancy desc limit 5");

        longestLifeRecord = sparkSession
                .sql("select State, Life_Expectancy  from life order by Life_Expectancy desc");

        shortestLifeRecord = sparkSession
                .sql("select State, Life_Expectancy  from life order by Life_Expectancy asc");

        removeNullsShortestLifeRecord = shortestLifeRecord.where("Life_Expectancy is not null");
    }

    private void bucketBuilder(Dataset<Row> dataset, String bucketName) {
        dataset.coalesce(1)
                .write()
                .format("csv")
                .option("header", "true")
                .mode(SaveMode.Overwrite)
                .save("s3://p2storage-jsd/" + bucketName);
    }
}
// State,
// County,
// Life_Expectancy,
// Life_Expectancy_Range,
// Life_Expectancy_Standard_Error


//Alabama,"Autauga County, AL",0201.00,73.1,56.9-75.1,2.2348
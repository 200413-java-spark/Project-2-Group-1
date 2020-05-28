package com.github.sparkBusiness.sparkSQL;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SaveMode;
import org.apache.spark.sql.SparkSession;

public class DanielSQL implements SQLInt {

    public Dataset<Row> dataset;
    public SparkSession sparkSession;

    private Dataset<Row> StateswithAvgabove80;
    private Dataset<Row> CountieswithAvgabove80;
    private Dataset<Row> StateswithAvgbelow60;
    private Dataset<Row> CountieswithAvgbelow60;

    public DanielSQL(Dataset<Row> dataset, SparkSession sparkSession) {
        this.dataset = dataset;
        this.sparkSession = sparkSession;
    }

    @Override
    public void run() {

        dataset.createOrReplaceTempView("stats");
        dataBuilder();
        bucketBuilder(StateswithAvgabove80, "output4");
        bucketBuilder(CountieswithAvgabove80, "output5");
        bucketBuilder(StateswithAvgbelow60, "output6");
        bucketBuilder(CountieswithAvgbelow60, "output7");

    }

    private void dataBuilder() {
        StateswithAvgabove80 = sparkSession
        .sql("select State, format_number(avg(Life_Expectancy), 2) as Average_Above_80 from stats where Life_Expectancy >= 80 group by State order by Average_Above_80 asc limit 10");

        CountieswithAvgabove80 = sparkSession
        .sql("select County, format_number(avg(Life_Expectancy), 2) as Average_Above_80 from stats where Life_Expectancy >= 80 group by County order by Average_Above_80 asc limit 10");

        StateswithAvgbelow60 = sparkSession
        .sql("select State, format_number(avg(Life_Expectancy), 2) as Average_Below_60 from stats where Life_Expectancy <= 60 group by State order by Average_Below_60 asc limit 10");

        CountieswithAvgbelow60 = sparkSession
        .sql("select County, format_number(avg(Life_Expectancy), 2) as Average_Below_60 from stats where Life_Expectancy <= 60 group by County order by AAverage_Below_60 asc limit 10");
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
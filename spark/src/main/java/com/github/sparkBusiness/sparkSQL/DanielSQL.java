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
//        bucketBuilder(StateswithAvgabove80, "output0");
//        bucketBuilder(CountieswithAvgabove80, "output1");
//        bucketBuilder(StateswithAvgbelow60, "output2");
//        bucketBuilder(CountieswithAvgbelow60, "output3");

        StateswithAvgabove80.where("Average_Above_80 > 80").show();
        CountieswithAvgabove80.where("Average_Above_80 > 80").show();
        StateswithAvgbelow60.where("Average_Below_60 < 60").show();
        CountieswithAvgbelow60.where("Average_Below_60 < 60").show();

    }

    private void dataBuilder() {
        StateswithAvgabove80 = sparkSession
                .sql("select State, format_number(avg(Life_Expectancy), 2) as Average_Above_80 from stats group by State order by Average_Above_80 asc");

        CountieswithAvgabove80 = sparkSession
                .sql("select County, format_number(avg(Life_Expectancy), 2) as Average_Above_80 from stats group by County order by Average_Above_80 asc");

        StateswithAvgbelow60 = sparkSession
                .sql("select State, format_number(avg(Life_Expectancy), 2) as Average_Below_60 from stats group by State order by Average_Below_60 asc");

        CountieswithAvgbelow60 = sparkSession
                .sql("select County, format_number(avg(Life_Expectancy), 2) as Average_Below_60 from stats group by County order by Average_Below_60 asc");
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
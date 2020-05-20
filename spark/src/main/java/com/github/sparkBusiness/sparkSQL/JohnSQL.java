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

        dataset.createOrReplaceTempView("gun_violence");
        Dataset<Row> results = sparkSession.sql("select incident_id from gun_violence");
        results.show();
    }
}
//incident_id,
// date,state,
// city_or_county,
// address,n_killed,
// n_injured,incident_url,
// source_url,
// incident_url_fields_missing,
// congressional_district,
// gun_stolen,
// gun_type,
// incident_characteristics,
// latitude,
// location_description,
// longitude,
// n_guns_involved,
// notes,
// participant_age,
// participant_age_group,
// participant_gender,
// participant_name,
// participant_relationship,
// participant_status,
// participant_type,
// sources,
// state_house_district,
// state_senate_district

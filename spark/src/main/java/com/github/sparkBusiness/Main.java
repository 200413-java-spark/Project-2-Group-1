package com.github.sparkBusiness;

import com.github.sparkBusiness.sparkSQL.MasterSQL;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

public class Main {
        public static void main(String[] args) {
//                LOCAL:
                SparkSession sparkSession = SparkSession
                        .builder()
                        .appName("spark-job")
                        .master("local[*]")
                        .getOrCreate();

                Dataset<Row> dataset = sparkSession
                        .read()
                        .option("header", true)
                        .csv("spark/life.csv");
//                LOCAL:

//                EMR:
//                SparkSession sparkSession = SparkSession
//                        .builder()
//                        .appName("spark-job")
//                        .getOrCreate();
//
//                Dataset<Row> dataset = sparkSession
//                        .read()
//                        .option("header", true)
//                        .csv("s3://p2storage-jsd/life.csv");
//                EMR:


                MasterSQL masterSQL = new MasterSQL(dataset, sparkSession);

                masterSQL.runQ();

                // System.out.println("========== Print Data ==============");
                // dataset.show();

                // Creating client connection
//                AWSCredentials credentials = new BasicAWSCredentials("AKIAVHITM6YLVOI6OROV", "8EUqcjoEn3SGWpcxhQ2DdfTTQqN52CEQDC6xHfF/");
//
//                AmazonS3 s3client = AmazonS3ClientBuilder.standard()
//                                .withCredentials(new AWSStaticCredentialsProvider(credentials))
//                                .withRegion(Regions.US_EAST_2).build();
//
//                // Check to see if bucket exists
//                String bucketName = "census-bucket";
//                if (s3client.doesBucketExist(bucketName)) {
//                        Log.info("Bucket name is not available." + "Try again with a different Bucket name.");
//                        return;
//                }
//                // Create bucket
//                s3client.createBucket(bucketName);
//
//                // List the bucket
//                List<Bucket> buckets = s3client.listBuckets();
//                for (Bucket bucket : buckets) {
//                        System.out.println(bucket.getName());
//                }
//
//                // Uploading the file
//                s3client.putObject("census-bucket",
//                                "Project-2\\U.S._Life_Expectancy_at_Birth_by_State_and_Census_Tract_-_2010-2015.csv",
//                                new File("C:\\Users\\Daniel\\Desktop\\Project-2\\U.S._Life_Expectancy_at_Birth_by_State_and_Census_Tract_-_2010-2015.csv"));
//
//                // Listing Objects
//                ObjectListing objectListing = s3client.listObjects("census-bucket");
//                for (S3ObjectSummary os : objectListing.getObjectSummaries()) {
//                        Log.info(os.getKey());
//                }
//
//                // Downloading an Object
//                com.amazonaws.services.s3.model.S3Object s3object = s3client.getObject("census-bucket",
//                                "Project-2\\U.S._Life_Expectancy_at_Birth_by_State_and_Census_Tract_-_2010-2015.csv");
//                S3ObjectInputStream inputStream = s3object.getObjectContent();
//                try {
//                        FileUtils.copyInputStreamToFile(inputStream, new File(
//                                        "C:\\Users\\Daniel\\Desktop\\U.S._Life_Expectancy_at_Birth_by_State_and_Census_Tract_-_2010-2015.csv"));
//                } catch (IOException e) {
//                        e.printStackTrace();
//                        System.out.println("File already exist");
//                }

    }
}

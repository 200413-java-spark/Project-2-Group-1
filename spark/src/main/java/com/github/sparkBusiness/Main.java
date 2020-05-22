package com.github.sparkBusiness;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.esotericsoftware.minlog.Log;
import com.github.sparkBusiness.sparkSQL.MasterSQL;

import org.apache.commons.io.FileUtils;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

public class Main {
        public static void main(String[] args) {

//                SparkSession sparkSession = SparkSession.builder().appName("spark-job").master("local[*]").getOrCreate();
                SparkSession sparkSession = SparkSession
                        .builder()
                        .appName("spark-job")
                        .getOrCreate();

                Dataset<Row> dataset = sparkSession
                        .read()
                        .option("header", true)
                        .csv("s3n://p2storage-jsd/life.csv");

//         bucket name       p2storage-jsd
//         "AKIAVHITM6YLVOI6OROV", "8EUqcjoEn3SGWpcxhQ2DdfTTQqN52CEQDC6xHfF/"

                // Dataset<Row> dataset = sparkSession
                // .read()
                // .option("header", true)
                // .csv("C:\\Users\\johnm\\IdeaProjects\\Project-2-Group-1\\web\\guns-10.csv");
                //
                // Dataset<Row> dataset = sparkSession
                // .read()
                // .option("header", true)
                // .csv("C:\\Users\\johnm\\IdeaProjects\\Project-2-Group-1\\web\\guns-10.csv");

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

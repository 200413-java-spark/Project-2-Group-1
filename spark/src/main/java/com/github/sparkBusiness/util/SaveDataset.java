package com.github.sparkBusiness.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;

public class SaveDataset {

    public void save(Dataset<Row> result, String[] columnNames, int numberOfColumns, String filePath) {
        if (columnNames.length != numberOfColumns)
            return;
        List<Row> data = result.collectAsList();
        List<String> rows = formatRows(data, columnNames, numberOfColumns);
        File file = writeToFile(rows, filePath);
        uploadToS3(file);
    }

    public List<String> formatRows(List<Row> datasetRows, String[] columnNames, int numberOfColumns) {
        if (columnNames.length != numberOfColumns)
            return null;
        String headers = columnNames[0];
        for (int h = 0; h < columnNames.length - 1; h++) {
            headers = headers.concat("," + columnNames[h + 1]);
        }
        List<String> rows = new ArrayList<String>();
        rows.add(headers);
        String row = null;
        String[] values = null;
        boolean skipRow = false;
        for (Row r : datasetRows) {
            values = r.toString().replace("[", "").replace("]", "").replace(", ", ",").split(",");
            if (values.length != numberOfColumns || values[0] == null || values[0].isEmpty() || values[0].equals("")
                    || values[0].equals("(blank)"))
                continue;
            row = values[0];
            for (int i = 0; i < values.length - 1; i++) {
                if (values[i + 1] == null || values[i + 1].isEmpty() || values[i + 1].equals("")
                        || values[0].equals("(blank)"))
                    skipRow = true;
                else if (values[i + 1].contains(" "))
                    row = row.concat(",").concat('"' + values[i + 1] + '"');
                else
                    row = row.concat(",").concat(values[i + 1]);
            }
            if (skipRow)
                skipRow = false;
            else
                rows.add(row);
        }
        return rows;
    }

    public File writeToFile(List<String> lines, String filePath) {
        File file = new File(filePath);
        try {
            FileWriter fw = new FileWriter(file);
            BufferedWriter writer = new BufferedWriter(fw);
            if (!file.exists()) {
                file.createNewFile();
            }
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
            writer.close();
            return file;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void uploadToS3(File file) {
        if (file == null) return;
        // export file to S3
        Regions clientRegion = Regions.DEFAULT_REGION; //Add correct region
        String bucketName = "Bucket name"; // Add correct bucket name
        String key = "String object key name"; // Add correct Key
        //String fileName = "filename";

        try {
            AmazonS3 s3client = AmazonS3ClientBuilder.standard()
                .withRegion(clientRegion)
                .build();
            //Upload a file as a new object with the ContentType and title specified
            PutObjectRequest request = new PutObjectRequest(bucketName, key, file);
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentType("csv");
            metadata.addUserMetadata("title", "outputFile");
            request.setMetadata(metadata);
            s3client.putObject(request);
        } catch (AmazonServiceException e) {
            //The Call was transmitted successfully, but Amazon S3 couldn't proccess
            //it. so it returns an error message response.
            e.printStackTrace();
        } catch (SdkClientException e) {
            //Amazon S3 couldn't be contacted for a response, or the client
            // couldn't parse the response from Amazon S3
            e.printStackTrace();
        }
    }    
}
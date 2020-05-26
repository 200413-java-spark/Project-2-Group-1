package com.github.sparkBusiness.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;

public class SaveDataset {

    public void save(Dataset<Row> result, String[] columnNames, int numberOfColumns, String filePath) {
        if (columnNames.length != numberOfColumns) return;
        List<Row> data = result.collectAsList();
        List<String> rows = formatRows(data, columnNames, numberOfColumns);
        File file = writeToFile(rows, filePath);
        uploadToS3(file);
    }

    public List<String> formatRows(List<Row> datasetRows, String[] columnNames, int numberOfColumns) {
        if (columnNames.length != numberOfColumns) return null;
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
            if (values.length != numberOfColumns || values[0] == null || values[0].isEmpty() || values[0].equals("") || values[0].equals("(blank)")) continue;
            row = values[0];
            for (int i = 0; i < values.length - 1; i++) {
                if(values[i + 1] == null || values[i +1].isEmpty() || values[i + 1].equals("") || values[0].equals("(blank)")) skipRow = true;
                else if(values[i + 1].contains(" ")) row = row.concat(",").concat('"' + values[i + 1] + '"');
                else row = row.concat(",").concat(values[i + 1]);
            }
            if (skipRow) skipRow = false;
            else rows.add(row);
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

    }
    
}
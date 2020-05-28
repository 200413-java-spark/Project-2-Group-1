package output.util;

import output.s3.GetS3File;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

public class InitOutput {
    ArrayList<String> folderNameList;

    public InitOutput(String[] args) {

        if (args.length == 0) {
            folderNameList.add("output0");
            folderNameList.add("output1");
            folderNameList.add("output2");
            folderNameList.add("output3");
        } else {
            folderNameList = new ArrayList<String>(Arrays.asList(args));
        }

        for (String folderName : folderNameList) {
            GetS3File getS3File = new GetS3File(folderName);
            getS3File.run();

            CsvFileReader csvFileReader = new CsvFileReader(getS3File.getFileName());
            try {
                csvFileReader.run();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}

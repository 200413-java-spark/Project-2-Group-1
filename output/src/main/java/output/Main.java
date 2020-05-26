package output;

import output.s3.GetS3File;
import output.util.CsvFileReader;

import java.io.IOException;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws IOException, SQLException {

        GetS3File getS3File = new GetS3File();

        getS3File.run();

        CsvFileReader csvFileReader = new CsvFileReader(getS3File.getFileName());
        csvFileReader.run();
    }
}

 
//public class Main {
//
//    public static void main(String[] args) throws IOException {
//
//        GetS3File getS3File = new GetS3File();
//
//        getS3File.run();
//
//        CsvFileReader csvFileReader = new CsvFileReader(getS3File.getFileName());
//        csvFileReader.run();
//
////        if (resetDatabase()) {
////            extract();
////            transform();
////            load();
////        }
////    }
////
////    private static boolean resetDatabase() {
////        return DaoUtil.clearTables();
////    }
////
////    private static void extract() {
////        // GetS3File file1 = new GetS3File();
////        // file1.run();
////    }
////
////    private static void transform() {
////        TransformRegionData.execute();
////        TransformStateData.execute();
////        TransformCountyData.execute();
////    }
////
////    private static void load() {
////        LoadRegionData.execute();
////        LoadStateData.execute();
////        LoadCountyData.execute();
//    }
//
//}

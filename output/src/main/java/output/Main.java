package output;

import output.load.LoadRegionData;
import output.load.LoadStateData;
import output.load.LoadCountyData;
import output.s3.GetS3File;
import output.transform.TransformRegionData;
import output.transform.TransformStateData;
import output.transform.TransformCountyData;
import output.util.DaoUtil;

public class Main {

    public static void main(String[] args) {
        if (resetDatabase()) {
            extract();
            transform();
            load();
        }
    }

    private static boolean resetDatabase() {
        return DaoUtil.clearTables();
    }

    private static void extract() {
        // GetS3File file1 = new GetS3File();
        // file1.run();
    }

    private static void transform() {
        TransformRegionData.execute();
        TransformStateData.execute();
        TransformCountyData.execute();
    }
    
    private static void load() {
        LoadRegionData.execute();
        LoadStateData.execute();
        LoadCountyData.execute();
    }

}

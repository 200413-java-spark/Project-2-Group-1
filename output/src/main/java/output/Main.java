package output;

import java.util.List;

import output.s3.GetS3File;
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
    }
    
    private static void load() {
    }

}

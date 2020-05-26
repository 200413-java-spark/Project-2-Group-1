package spark.sparkSQL;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.github.sparkBusiness.util.SaveDataset;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class SaveDatasetTest {

    // mvn clean test -Dtest=SaveDatasetTest

    private static final String FILE_PATH = "src/test/resources/output/output.csv";
    private static SaveDataset saveDataset;
    
    @BeforeClass
    public static void setup() {
        saveDataset = new SaveDataset();
    }

    @Test
    public void writeToFileTest() {
        String line1 = "Alabama,Baldwin County,AL,0107.04,81,79.6-81.6,1.982";
        String line2 = "Alabama,Baldwin County,AL,0113.00,82.1,81.7-97.5,2.7204";
        List<String> lines = new ArrayList<String>();
        lines.add(line1);
        lines.add(line2);
        File file = saveDataset.writeToFile(lines, FILE_PATH);
        Assert.assertNotNull(file);
    }

}
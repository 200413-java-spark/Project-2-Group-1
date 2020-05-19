import com.github.sparkBusiness.MasterSQL;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"com.github.*"})
@EntityScan(basePackages = {"com.github.*"})
@EnableJpaRepositories(basePackages = {"com.github.*"})
public class SGVApplication {

    public static void main(String[] args) {
        SpringApplication.run(SGVApplication.class);

        SparkSession sparkSession = SparkSession
                .builder()
                .appName("spark-job")
                .master("local[*]")
                .config("spark.sql.warehouse.dir", "file:///c:/tmp/")
                .getOrCreate();

        Dataset<Row> dataset = sparkSession
                .read()
                .option("header", true)
                .csv("C:\\Users\\johnm\\IdeaProjects\\Project-2-Group-1\\web\\guns-10.csv");

//        Dataset<Row> dataset = sparkSession
//                .read()
//                .option("header", true)
//                .csv("C:\\Users\\johnm\\IdeaProjects\\Project-2-Group-1\\web\\guns-10.csv");
//
//        Dataset<Row> dataset = sparkSession
//                .read()
//                .option("header", true)
//                .csv("C:\\Users\\johnm\\IdeaProjects\\Project-2-Group-1\\web\\guns-10.csv");


        MasterSQL masterSQL = new MasterSQL(dataset, sparkSession);

        masterSQL.runQ();

//        System.out.println("========== Print Data ==============");
//        dataset.show();

    }
}

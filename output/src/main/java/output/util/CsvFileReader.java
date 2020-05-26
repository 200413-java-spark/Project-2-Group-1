package output.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;

public class CsvFileReader {

    String sql = "INSERT INTO averages (state, average) VALUES (?, ?)";
    Connection connection = null;
    PreparedStatement statement = null;
    private String fileName;


    public CsvFileReader(String fileName) {
        this.fileName = fileName;

    }

    public void run() throws IOException, SQLException {

        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Storage\\" + fileName))) {
            String line;
            boolean firstLine = true;
            connection = DaoUtil.getConnection();
            statement = connection.prepareStatement(sql);

            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (firstLine) {
                    firstLine = false;
                    continue;
                }
                statement.setString(1, values[0].toString());
                statement.setDouble(2, Double.parseDouble(values[1]));
                statement.addBatch();
            }
            statement.executeBatch();
        }


    }
}



package edu.wpi.teamg;

import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class DBConnection implements DAO {
    static Connection connection;
    public void setConnection(){
        try{
            connection=
                    DriverManager.getConnection(
                            getDBCreds().get(0),getDBCreds().get(1), getDBCreds().get(2)
                    );
        } catch (SQLException e) {
            System.err.println("SQL Exception");
        }
    }
    private List<String> getDBCreds(){
        List<String> creds = new LinkedList<>();
        try{
            InputStream is = new FileInputStream("creds.yml");
            Yaml yaml = new Yaml();
            Map<String, Object> data = yaml.load(is);
            creds.add(data.get("url").toString());
            creds.add(data.get("username").toString());
            creds.add(data.get("password").toString());

            return creds;
        } catch (FileNotFoundException e) {
            return null;
        }
    }

    public void closeConnection(){
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

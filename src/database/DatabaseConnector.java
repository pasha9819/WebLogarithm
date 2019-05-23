package database;

import java.io.IOException;
import java.sql.*;

import com.mysql.cj.jdbc.Driver;
public  class DatabaseConnector {

    private String URL = "jdbc:mysql://localhost:3306/tnp?" +
            "autoReconnect=true" +
            "&useSSL=false" +
            "&useLegacyDatetimeCode=false" +
            "&serverTimezone=UTC";
    private String USER = "user";
    private String PASSWORD = "password";
    private static Connection connection;
    private static Driver driver;
    private static Statement statement;
    private static DatabaseConnector instance;

    static {
        try {
            create();
        } catch (Exception ignored) {}
    }

    private static synchronized void create() throws SQLException {
        if (instance == null){
            instance = new  DatabaseConnector();
            instance.build();
        }
    }

    private DatabaseConnector()  {    }

    private synchronized void build() throws SQLException {
        driver = new Driver();
        DriverManager.registerDriver(driver);
        connection = DriverManager.getConnection(URL, USER, PASSWORD);
        statement = connection.createStatement();
    }

    public synchronized Statement getStatement() {
        return statement;
    }

    public synchronized Connection getConnection() {
        return connection;
    }

    public synchronized Driver getDriver() {
        return driver;
    }

    public synchronized void close() throws SQLException {
        connection.close();
        statement.close();
    }

    public static synchronized void insert(Double arg, Double base, Double result) throws SQLException {
        String statement = "INSERT INTO tnp.logarithm SET `arg` = ?, `base` = ?, `result` = ?;";
        PreparedStatement s = connection.prepareStatement(statement);
        s.setDouble(1, arg);
        s.setDouble(2, base);
        s.setDouble(3, result);
        s.executeUpdate();
    }

    public static synchronized Double select(Double arg, Double base) throws SQLException {
        String statement = "SELECT `result` FROM tnp.logarithm where `arg` = ? AND `base` = ?;";
        PreparedStatement s = connection.prepareStatement(statement);
        s.setDouble(1, arg);
        s.setDouble(2, base);
        ResultSet set = s.executeQuery();
        if (set.next()){
            return set.getDouble("result");
        }
        return null;
    }
}

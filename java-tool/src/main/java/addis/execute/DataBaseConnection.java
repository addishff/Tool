package addis.execute;

import java.sql.*;
import java.util.Arrays;

/**
 * Created by huangfeifeng on 2/26/16.
 */
public class DatabaseConnection {
    String mysqlUrl;
    Connection connection;
    Statement statement;
    String preSql;

    public DatabaseConnection(String mysqlUrl) throws SQLException {
        this.mysqlUrl = mysqlUrl;
        this.connection = DriverManager.getConnection(mysqlUrl, "root", "19910102");
        this.statement = connection.createStatement();
    }

    public DatabaseConnection() throws SQLException {
        this.mysqlUrl = "jdbc:mysql://127.0.0.1:3306/addis?useUnicode=true&amp";
        this.connection = DriverManager.getConnection(mysqlUrl, "root", "19910102");
        this.statement = connection.createStatement();
    }

    public boolean execute(String sql) throws SQLException {
        preSql = sql;
        return this.statement.execute(sql);
    }

    public boolean executePre() throws SQLException {
        return this.execute(preSql);
    }

    public boolean insert() throws SQLException {
        String smileBig = "\uD83D\uDE00";
        String smileSmall = "\uD83D\uDE0A";
        String sql = "insert into test (test_name,test_place) values ('" + smileBig + "','" + smileSmall + "');";
        return this.execute(sql);
    }

    public void select() throws SQLException {
        String sql = "select * from test";
        this.execute(sql);
        ResultSet resultSet = this.statement.getResultSet();
        while (resultSet.next()) {
            System.out.println(resultSet.getInt(1) + " " +
                    resultSet.getString(2) + " " +
                    Arrays.toString(resultSet.getBytes(3)));
        }
    }


    public static void main(String... args) throws SQLException {
        DatabaseConnection dataBaseConnection = new DatabaseConnection();
        dataBaseConnection.select();
        System.out.println();
        dataBaseConnection.insert();
        dataBaseConnection.select();
    }

}

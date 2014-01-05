package tour.dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBConnection {

    public DBConnection() {
    }

    public static Statement getStatement() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/test", "root", "root");
            Statement stmt = (Statement) con.createStatement();
            return stmt;
        } catch (Exception e) {
            return null;
        }
    }

    public static void executeUpdate(String query) {
        try {
            Statement stmt = getStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
        }
    }

    public static ResultSet executeQuery(String query) {
        try {
            Statement stmt = getStatement();
            return stmt.executeQuery(query);
        } catch (Exception e) {
            return null;
        }
    }
}
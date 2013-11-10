package mail.account;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import mail.dbconnection.DBConnection;

public class AccountDAO {

    public static void addNewAccount(String login, String password, String name, String surname, String age, String persCode) {
            String insert = "INSERT INTO account VALUES ('" + login + "','" + password + "','" + name +"','" + surname + "','" + age + "','" + persCode +"')";
            DBConnection.executeUpdate(insert);
    }

    public static List<Account> getAccountList() {
        try {
            List<Account> list = new ArrayList<Account>();
            String select = "SELECT * FROM account";
            ResultSet query = DBConnection.executeQuery(select);
            while (query.next()) {
                
                String login = query.getString("login");
                String password = query.getString("password");
                String name = query.getString("name");
                String surname = query.getString("surname");
                String age = query.getString("age");
                String persCode = query.getString("persCode");
                
                list.add(new Account(login, password, name, surname, age, persCode));
            }
            return list;
        } catch (Exception e) {
            return null;
        }
    }

    public static boolean checkLogin(String login) {
        boolean bool = false;
        try {
            String select= "SELECT login FROM account";
            ResultSet query = DBConnection.executeQuery(select);
            while (query.next()) {
                String username = query.getString("login");
                if (login.equals(username)) {
                    bool = true;
                    query.close();
                }
            }
            return bool;
        } catch (Exception e) {
            return bool;
        }
    }
}
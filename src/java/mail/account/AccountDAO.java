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
    
    public static void updateAccount(String login, String name, String surname, String age, String persCode){
            String insert = "UPDATE account SET name='" + name + "', surname='" + surname + "', age='" + age + "', persCode='" + persCode + "' WHERE login='" + login + "';";
            DBConnection.executeUpdate(insert);
    }
    
    public static void changePassword(String login, String newpassword){
            String update = "UPDATE account SET password='" + newpassword + "' WHERE login='" + login + "'";
            DBConnection.executeUpdate(update);
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
    
    public static boolean checkOldPassword(String login, String password) {
        boolean bool = false;
        try {
            String select= "SELECT password FROM account WHERE login='"+login+"'";
            ResultSet query = DBConnection.executeQuery(select);
            while (query.next()) {
                String userpassword = query.getString("password");
                if (password.equals(userpassword)) {
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
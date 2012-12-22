package dbtest;

import java.sql.ResultSet;
import mail.dbconnection.DBConnection;

public class Account {
    
    void add(String login, String password) {
            String insert = "INSERT INTO Account VALUES ('" + login + "','" + password + "')";
            DBConnection.executeUpdate(insert);
    }
    
    void check(String login){
        try{
            ResultSet query = DBConnection.executeQuery("SELECT * from Account");
            if(!(login.equals(query.toString()))) {query.next();}
            else {System.out.println("That login exist in database");}
        } catch (Exception e) {
        }
    }

    void delete(String login) {
        
            String delete = "DELETE FROM Account WHERE login = '"+login+"'";
            DBConnection.executeUpdate(delete);
            
    }

    void newPassword(String login, String password) {
            
            String update = "UPDATE Account SET password = '"+password+"' WHERE login = '"+login+"'";
            DBConnection.executeUpdate(update);
    }
 
}

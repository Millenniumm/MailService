package mail.mail;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import mail.dbconnection.DBConnection;
import mail.session.SignInSession;
import org.apache.wicket.Session;

public class MailDAO {

    String userinsession = ((SignInSession) Session.get()).getUser().toString();
    
    private List<Mail> mailList = new ArrayList<Mail>() {
        {   
            String select = "SELECT * FROM mail";
            getResult(DBConnection.executeQuery(select));
            
        }
    };
    
    private List<Mail> outboxList = new ArrayList<Mail>() {
        {
            
            String select = "SELECT * FROM mail WHERE sender='" + userinsession + "'";
            getResult(DBConnection.executeQuery(select));
            
        }
    };
    
    private List<Mail> inboxList = new ArrayList<Mail>() {
        {
            
            String select = "SELECT * FROM mail WHERE receiver='" + userinsession + "'";
            getResult(DBConnection.executeQuery(select));

        }
    };
    
    private List<Mail> spamList = new ArrayList<Mail>() {
        {
            String select = "SELECT * FROM mail WHERE receiver='" + userinsession + "' and spam = 0";
            getResult(DBConnection.executeQuery(select));
        }
    };
    
    private List<Mail> trashList = new ArrayList<Mail>() {
        {
            
            String select = "SELECT * FROM mail WHERE receiver='" + userinsession + "' and trash = 0";
            getResult(DBConnection.executeQuery(select));
            
        }
    };
    
    private List<Mail> draftList = new ArrayList<Mail>() {
        {
            
            String select = "SELECT * FROM mail WHERE sender='" + userinsession + "' and draft = 0";
            getResult(DBConnection.executeQuery(select)); 
            
        }
    };

    public void sentNewMail(Integer id, String receiver, String title, String message, String sender, Integer spam, Integer trash, Integer draft) {
       
        String insert = "INSERT INTO mail VALUES ('" + id + "','" + receiver + "','" + title + "','"
                   + message + "','" + sender + "','" + spam + "','" + trash + "','" + draft + "')";

        DBConnection.executeUpdate(insert);
        
    }

    public List<Mail> getInboxList() {
        return inboxList;
    }

    public List<Mail> getSpamList() {
        return spamList;
    }

    public List<Mail> getTrashList() {
        return trashList;
    }

    public List<Mail> getDraftList() {
        return draftList;
    }

    public List<Mail> getMailList() {
        return mailList;
    }

    public List<Mail> getOutboxList() {
        return outboxList;
    }

    public List<Mail> getResult(ResultSet query) {
        List<Mail> list = new ArrayList<Mail>();
        try {
            while (query.next()) {
                Integer id = query.getInt("id");
                String receiver = query.getString("receiver");
                String title = query.getString("title");
                String message = query.getString("message");
                String sender = query.getString("sender");
                Integer spam = query.getInt("spam");
                Integer trash = query.getInt("trash");
                Integer draft = query.getInt("draft");
                list.add(new Mail(id, receiver, title, message, sender, spam, trash, draft));
            }
            return list;
        } catch (Exception e) {
            return null;
        }
    }
}

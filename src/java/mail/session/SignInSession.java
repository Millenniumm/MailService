package mail.session;

import java.util.Iterator;
import mail.account.Account;
import mail.account.AccountDAO;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.request.Request;

public class SignInSession extends AuthenticatedWebSession {

    private String userName;

    protected SignInSession(Request request) {
        super(request);
    }

    @Override
    public final boolean authenticate(final String username, final String password) {

        Iterator itr = AccountDAO.getAccountList().iterator();
        
            if (userName == null) {

                while(itr.hasNext()){
                    Account user = (Account) itr.next();
                if (user.getLogin().equalsIgnoreCase(username) && user.getPassword().equalsIgnoreCase(password)) {
                    userName = username;
                }}
            }

            return userName != null;
        
    }

    public String getUser() {
        return userName;
    }

    public void setUser(final String user) {
        this.userName = user;
    }

    @Override
    public Roles getRoles() {
        return null;
    }
}

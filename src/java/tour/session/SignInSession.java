package tour.session;

import java.util.Iterator;
import tour.account.Account;
import tour.account.AccountDAO;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.request.Request;

public class SignInSession extends AuthenticatedWebSession {

    private String userName;
    private AccountDAO accDAO = new AccountDAO();

    protected SignInSession(Request request) {
        super(request);
    }

    @Override
    public final boolean authenticate(final String username, final String password) {

        Iterator itr = accDAO.getAccountList().iterator();
        
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

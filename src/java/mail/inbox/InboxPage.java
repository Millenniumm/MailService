package mail.inbox;

import java.util.Iterator;
import mail.base.BasePage;
import mail.mail.Mail;
import mail.session.AuthenticatedWebPage;

public class InboxPage extends BasePage implements AuthenticatedWebPage{

    public InboxPage() {
    }
    
    @Override
    protected Iterator<Mail> getEntityList() {
        return maildao.getInboxList().iterator();
    }

}

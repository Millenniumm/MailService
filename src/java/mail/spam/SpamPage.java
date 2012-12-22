package mail.spam;

import java.util.Iterator;
import mail.base.BasePage;
import mail.mail.Mail;

public class SpamPage extends BasePage {

    public SpamPage() {
    }

    @Override
    protected Iterator<Mail> getEntityList() {
        return maildao.getSpamList().iterator();
    }
    
}

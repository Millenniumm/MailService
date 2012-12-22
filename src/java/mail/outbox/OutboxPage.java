package mail.outbox;

import java.util.Iterator;
import mail.base.BasePage;
import mail.mail.Mail;

public class OutboxPage extends BasePage {

    public OutboxPage() {
    }

    @Override
    protected Iterator<Mail> getEntityList() {
        return maildao.getOutboxList().iterator();
    }
}

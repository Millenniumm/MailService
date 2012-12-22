package mail.draftbox;

import java.util.Iterator;
import mail.base.BasePage;
import mail.mail.Mail;

public class DraftboxPage extends BasePage{

    public DraftboxPage() {
    }

    @Override
    protected Iterator<Mail> getEntityList() {
      return maildao.getDraftList().iterator();
    }

}

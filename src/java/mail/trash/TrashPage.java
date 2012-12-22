package mail.trash;

import java.util.Iterator;
import mail.base.BasePage;
import mail.mail.Mail;

public class TrashPage extends BasePage{

    public TrashPage() {
    }
    
    @Override
    protected Iterator<Mail> getEntityList() {
         return maildao.getTrashList().iterator();
    }
}

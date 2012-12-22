package mail.sent;

import java.util.Iterator;
import mail.menupanel.MenuPanel;
import mail.mail.Mail;
import mail.mail.MailDAO;
import mail.session.SignInSession;
import org.apache.wicket.Session;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.PropertyModel;

public class SentPage extends WebPage {

    Mail mail = new Mail();
    MailDAO maildao = new MailDAO();
    Integer id;

    public SentPage() {

        Iterator<Mail> itr = maildao.getMailList().iterator();
        while (itr.hasNext()) {
            Mail mailcount = itr.next();
            id = mailcount.getID();
        }

        add(new MenuPanel("menuPanel"));

        final TextField receiverTF = new TextField("receiver", new PropertyModel<String>(mail, "receiver"));
        final TextField titleTF = new TextField("title", new PropertyModel<String>(mail, "title"));
        final TextArea messageTA = new TextArea("message", new PropertyModel<String>(mail, "message"));

        Button sent = new Button("sentMail") {
            @Override
            public void onSubmit() {
                String reciver = receiverTF.getValue();
                String title = titleTF.getValue();
                String message = messageTA.getValue();
                String sender = ((SignInSession) Session.get()).getUser().toString();
                Integer messageID = id + 1;
                maildao.sentNewMail(messageID, reciver, title, message, sender, 1, 1, 1);
            }
        };

        Button save = new Button("saveDrafts") {
            @Override
            public void onSubmit() {
                String saveReciver = receiverTF.getValue();
                String saveTitle = titleTF.getValue();
                String saveMessage = messageTA.getValue();
                String saveSender = ((SignInSession) Session.get()).getUser().toString();
                Integer saveMessageID = id + 1;
                maildao.sentNewMail(saveMessageID, saveReciver, saveTitle, saveMessage, saveSender, 1, 1, 0);
            }
        };

        Form logForm = new Form("mailForm");
        logForm.add(receiverTF, titleTF, messageTA, sent, save);
        add(logForm);
    }
}
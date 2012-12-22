package mail.read;

import java.util.Iterator;
import mail.mail.Mail;
import mail.mail.MailDAO;
import mail.menupanel.MenuPanel;
import mail.sent.SentPage;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class ReadMailPage extends WebPage {

    public ReadMailPage(final PageParameters parameters) {
        add(new MenuPanel("menuPanel"));
        
        String messageID = parameters.get("id").toString(); 
       
        MailDAO maildao = new MailDAO();
        Iterator<Mail> itr = maildao.getMailList().iterator();
        
        while (itr.hasNext()) {
            final Mail read = itr.next();
            if(messageID.equals(read.getID().toString()))
            {
            Form readForm = new Form("readForm");
            readForm.add(new Label("sender", read.getSender()));
            readForm.add(new Label("title", read.getTitle()));
            readForm.add(new Label("message", read.getMessage()));
            readForm.add(new Button("reply"){
            @Override
            public void onSubmit() {
                setResponsePage(SentPage.class);
            }
            });
            add(readForm);
            }
        }     
    }
}

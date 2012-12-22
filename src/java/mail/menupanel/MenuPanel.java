package mail.menupanel;

import mail.draftbox.DraftboxPage;
import mail.inbox.InboxPage;
import mail.outbox.OutboxPage;
import mail.sent.SentPage;
import mail.session.SignInSession;
import mail.signinout.SignOutPage;
import mail.spam.SpamPage;
import mail.trash.TrashPage;
import org.apache.wicket.Session;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;

public class MenuPanel extends Panel {

    public MenuPanel(String id) {
        super(id);
        
        add(new Label("welcome", "Hello, " + ((SignInSession) Session.get()).getUser().toString() + "! "));
        
        add(new Link("exit") {
            @Override
            public void onClick() {
                setResponsePage(SignOutPage.class);
            }
        });
        
        add(new Link("SentPageLink") {
            @Override
            public void onClick() {
                setResponsePage(SentPage.class);
            }
        });
        add(new Link("InboxPageLink") {
            @Override
            public void onClick() {
                setResponsePage(InboxPage.class);
            }
        });
        add(new Link("OutboxPageLink") {
            @Override
            public void onClick() {
                setResponsePage(OutboxPage.class);
            }
        });
        add(new Link("SpamPageLink") {
            @Override
            public void onClick() {
                setResponsePage(SpamPage.class);
            }
        });
        add(new Link("DraftboxPageLink") {
            @Override
            public void onClick() {
                setResponsePage(DraftboxPage.class);
            }
        });
        add(new Link("TrashPageLink") {
            @Override
            public void onClick() {
                setResponsePage(TrashPage.class);
            }
        });
    }
}
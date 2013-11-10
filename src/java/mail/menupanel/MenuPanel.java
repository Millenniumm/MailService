package mail.menupanel;

import mail.session.SignInSession;
import mail.signinout.SignOutPage;
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
    }
}
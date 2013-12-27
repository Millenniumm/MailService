package mail.menupanel;

import mail.account.AccountDAO;
import mail.session.SignInSession;
import mail.signinout.SignOutPage;
import org.apache.wicket.Session;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import tour.profile.Profile;
import tour.admin.AdminPage;

public class MenuPanel extends Panel {

    public MenuPanel(String id) {
        super(id);

        add(new Label("welcome", "Welcome, "));
        add(new Link("profileLink") {
            @Override
            public void onClick() {
                setResponsePage(Profile.class);
            }
        }.add(new Label("name", ((SignInSession) Session.get()).getUser().toString())));

//        if (AccountDAO.checkIsAdmin(((SignInSession) Session.get()).getUser().toString())) {
            add(new Link("adminPage") {
                @Override
                public void onClick() {
                    setResponsePage(AdminPage.class);
                }
            });
//        }

        add(new Link("exit") {
            @Override
            public void onClick() {
                setResponsePage(SignOutPage.class);
            }
        });
    }
}

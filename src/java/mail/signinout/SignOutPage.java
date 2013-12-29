package mail.signinout;

import org.apache.wicket.markup.html.WebPage;
import tour.header.HeaderPanel;

public final class SignOutPage extends WebPage {

    public SignOutPage() {
        add(new HeaderPanel("headerPanel"));
        getSession().invalidate();
    }
}

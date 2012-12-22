package mail.signinout;

import org.apache.wicket.markup.html.WebPage;

public final class SignOutPage extends WebPage {

    public SignOutPage() {
        getSession().invalidate();
    }
}

package tour.profile;

import mail.account.Account;
import mail.menupanel.MenuPanel;
import org.apache.wicket.markup.html.WebPage;

/**
 *
 * @author Valentin
 */
public final class Profile extends WebPage {

    Account acc = new Account();

    public Profile() {
        add(new MenuPanel("menuPanel"));
    }
}

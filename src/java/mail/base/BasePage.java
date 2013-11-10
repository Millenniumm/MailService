package mail.base;

import java.util.Iterator;
import mail.mail.Mail;
import mail.mail.MailDAO;
import mail.menupanel.MenuPanel;
import mail.read.ReadMailPage;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public abstract class BasePage extends WebPage {

    protected MailDAO maildao;
    
    public BasePage() {
        add(new MenuPanel("menuPanel"));

        maildao = new MailDAO();
        Iterator<Mail> itr = getEntityList();

        RepeatingView repeat = new RepeatingView("repeatMail");
        add(repeat);

        while (itr.hasNext()) {

            Mail mail = itr.next();

            PageParameters pageParameters = new PageParameters();

            WebMarkupContainer parent = new WebMarkupContainer(repeat.newChildId());
            repeat.add(parent);

            BookmarkablePageLink link = new BookmarkablePageLink("link", ReadMailPage.class, pageParameters.set("id", mail.getID()));
            parent.add(link);

            link.add(new Label("mail", "Title: " + mail.getTitle() + " From: " + mail.getSender()));
        }
    }

    protected abstract Iterator<Mail> getEntityList();
}

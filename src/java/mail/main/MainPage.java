package mail.main;

import mail.base.BasePage;
import mail.session.AuthenticatedWebPage;
import mail.menupanel.MenuPanel;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import tour.header.HeaderPanel;
import tour.order.OrderPage;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Valentin
 */
public final class MainPage extends BasePage implements AuthenticatedWebPage {

    public MainPage() {
        add(new HeaderPanel("headerPanel"));
        add(new MenuPanel("menuPanel"));
        add(new BookmarkablePageLink<OrderPage>("OrderPage", OrderPage.class));
    }
}

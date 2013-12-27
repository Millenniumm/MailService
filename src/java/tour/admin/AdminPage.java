/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tour.admin;

import mail.main.MainPage;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import tour.admin.functions.CreateOrderForUser;
import tour.admin.functions.DeleteOrder;

/**
 *
 * @author Deniss
 */
public final class AdminPage extends WebPage {

    public AdminPage() {
        super();
        add(new BookmarkablePageLink<DeleteOrder>("DeleteOrder", DeleteOrder.class));
        add(new BookmarkablePageLink<CreateOrderForUser>("CreateOrderForUser", CreateOrderForUser.class));
        add(new BookmarkablePageLink<MainPage>("MainPage", MainPage.class));
    }

    public AdminPage(PageParameters params) {
        //TODO:  process page parameters
    }
}

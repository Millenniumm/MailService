/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tour.admin.functions;

import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import tour.admin.AdminPage;

/**
 *
 * @author Deniss
 */
public final class CreateOrderForUser extends WebPage {

    public CreateOrderForUser() {
        super();
        add(new BookmarkablePageLink<AdminPage>("AdminPage", AdminPage.class));
    }
    
    public CreateOrderForUser(PageParameters params) {
    }
}

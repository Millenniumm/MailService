
package tour.admin;

import tour.main.MainPage;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import tour.admin.functions.CreateOrderForUser;
import tour.admin.functions.DeleteOrder;
import tour.admin.functions.SearchOrders;

/**
 *
 * @author Deniss
 */
public final class AdminPage extends WebPage {

    public AdminPage() {
        super();
        add(new BookmarkablePageLink<SearchOrders>("SearchOrders", SearchOrders.class));
        add(new BookmarkablePageLink<CreateOrderForUser>("CreateOrderForUser", CreateOrderForUser.class));
        add(new BookmarkablePageLink<MainPage>("MainPage", MainPage.class));
    }

    public AdminPage(PageParameters params) {
        //TODO:  process page parameters
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tour.profile.history;

import java.util.List;
import tour.menupanel.MenuPanel;
import tour.session.SignInSession;
import org.apache.wicket.Session;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.PropertyModel;
import tour.header.HeaderPanel;
import tour.order.OrderDao;
import tour.order.models.Order;

/**
 *
 * @author Deniss
 */
public final class UserOrderHistory extends WebPage {

    public UserOrderHistory() {
        super();
        add(new HeaderPanel("headerPanel"));
        add(new MenuPanel("menuPanel"));
        List<Order> orders = OrderDao.getOrdersForUser(((SignInSession) Session.get()).getUser().toString());
        Form form = new Form("form");
        form.add(new ListView<Order>("orders", orders) {
            @Override
            protected void populateItem(final ListItem<Order> li) {
                li.add(new Label("country", new PropertyModel(li.getModel(), "getCountry")));
                li.add(new Label("hotel", new PropertyModel(li.getModel(), "getHotel")));
                li.add(new Label("tour", new PropertyModel(li.getModel(), "getTour")));
                li.add(new Label("cost", new PropertyModel(li.getModel(), "getCost")));
            }
        });
        add(form);
    }
    
    public UserOrderHistory(PageParameters params) {
        //TODO:  process page parameters
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tour.admin.functions;

import java.util.List;
import mail.registration.RegistrationPage;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.PropertyModel;
import tour.admin.AdminPage;
import tour.order.OrderDao;
import tour.order.models.Order;

/**
 *
 * @author Deniss
 */
public final class DeleteOrder extends WebPage {

    public DeleteOrder() {
        super();
        OrderDao orderDao = new OrderDao();
        add(new BookmarkablePageLink<AdminPage>("AdminPage", AdminPage.class));
        List<Order> orders = OrderDao.getOrders();
        Form form = new Form("form");
        form.add(new ListView<Order>("orders", orders) {
            @Override
            protected void populateItem(final ListItem<Order> li) {
                final PropertyModel buttonId = new PropertyModel(li.getModel(), "getId");
                
                li.add(new Label("order", new PropertyModel(li.getModel(), "getOrder")));
                li.add(new Button("deleteButton",buttonId) {
                    @Override
                    public void onSubmit() {
                        String id = buttonId.getObject().toString();
                        OrderDao.removeOrderById(id);
                        setResponsePage(DeleteOrder.class);
                    }
                });
            }
        });
        add(form);
    }

    public DeleteOrder(PageParameters params) {
        //TODO:  process page parameters
    }
}

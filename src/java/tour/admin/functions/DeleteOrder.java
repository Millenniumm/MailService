/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tour.admin.functions;

import java.util.List;
import tour.menupanel.MenuPanel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
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
public final class DeleteOrder extends WebPage {

    private String feedBack;

    public DeleteOrder() {
        super();
    }

    public DeleteOrder(final PageParameters params) {
        feedBack = params.get("feedBack").toString();
        add(new HeaderPanel("headerPanel"));
        add(new MenuPanel("menuPanel"));

        OrderDao orderDao = new OrderDao();
        final PageParameters pageParams = new PageParameters();
        List<Order> orders = OrderDao.getOrdersForUser(params.get("userName").toString());
        Form form = new Form("form");
        form.add(new Label("feedBack", feedBack));
        form.add(new ListView<Order>("orders", orders) {
            @Override
            protected void populateItem(final ListItem<Order> li) {
                final PropertyModel buttonId = new PropertyModel(li.getModel(), "getId");
                li.add(new Label("country", new PropertyModel(li.getModel(), "getCountry")));
                li.add(new Label("hotel", new PropertyModel(li.getModel(), "getHotel")));
                li.add(new Label("tour", new PropertyModel(li.getModel(), "getTour")));
                li.add(new Label("user", new PropertyModel(li.getModel(), "getUser")));
                li.add(new Label("cost", new PropertyModel(li.getModel(), "getCost")));
                li.add(new Button("deleteButton") {
                    @Override
                    public void onSubmit() {
                        String id = buttonId.getObject().toString();
                        OrderDao.removeOrderById(id);
                        pageParams.add("userName", params.get("userName").toString());
                        pageParams.add("feedBack", "Deleted !");
                        setResponsePage(new DeleteOrder(pageParams));
                    }
                });
            }
        });
        add(form);
    }
}

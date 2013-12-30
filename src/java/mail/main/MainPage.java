package mail.main;

import java.util.ArrayList;
import java.util.List;
import mail.base.BasePage;
import mail.session.AuthenticatedWebPage;
import mail.menupanel.MenuPanel;
import mail.session.SignInSession;
import org.apache.wicket.Session;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import tour.header.HeaderPanel;
import tour.order.ComfirmationPage;
import tour.order.OrderPage;
import tour.order.models.Order;

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
        final PageParameters pageParams = new PageParameters();
        add(new HeaderPanel("headerPanel"));
        add(new MenuPanel("menuPanel"));

        Form form = new Form("form");
        List<Order> orders = new ArrayList();
        orders.add(new Order("Zimbvabva", "Jellki", "River side Cruse", ((SignInSession) Session.get()).getUser().toString(), "200"));
        orders.add(new Order("America", "Washington", "Airplane Ride", ((SignInSession) Session.get()).getUser().toString(), "1200"));
        orders.add(new Order("Spain", "Madrid", "Run with the bulls", ((SignInSession) Session.get()).getUser().toString(), "2200"));
        orders.add(new Order("China", "Hong-Kong", "Bus Ride", ((SignInSession) Session.get()).getUser().toString(), "400"));
        orders.add(new Order("Latvia", "Daugavpils", "Wall climing", ((SignInSession) Session.get()).getUser().toString(), "550"));
        form.add(new ListView<Order>("orders", orders) {
            @Override
            protected void populateItem(final ListItem<Order> li) {
                li.add(new Label("country", new PropertyModel(li.getModel(), "getCountry")));
                li.add(new Label("hotel", new PropertyModel(li.getModel(), "getHotel")));
                li.add(new Label("tour", new PropertyModel(li.getModel(), "getTour")));
                li.add(new Label("cost", new PropertyModel(li.getModel(), "getCost")));
                li.add(new Button("hotOrderButton") {
                    @Override
                    public void onSubmit() {
                        pageParams.add("country", new PropertyModel(li.getModel(), "getCountry").getObject());
                        pageParams.add("hotel", new PropertyModel(li.getModel(), "getHotel").getObject());
                        pageParams.add("tour", new PropertyModel(li.getModel(), "getTour").getObject());
                        pageParams.add("user", ((SignInSession) Session.get()).getUser());
                        pageParams.add("cost", new PropertyModel(li.getModel(), "getCost").getObject());
                        setResponsePage(new ComfirmationPage(pageParams));
                    }
                });
            }
        });
        add(form);
    }
}

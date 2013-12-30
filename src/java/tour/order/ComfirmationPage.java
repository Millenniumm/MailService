/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tour.order;

import mail.main.MainPage;
import mail.menupanel.MenuPanel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.behavior.* ;
import tour.header.HeaderPanel;

/**
 *
 * @author Deniss
 */
public final class ComfirmationPage extends WebPage {

    private OrderDao orderDao;

    public ComfirmationPage() {
        super();
    }

    public ComfirmationPage(final PageParameters params) {
        orderDao = new OrderDao();
        add(new MenuPanel("menuPanel"));
        add(new HeaderPanel("headerPanel"));
        Form form = new Form("form");
        form.add(new Label("country", params.get("country").toString()));
        form.add(new Label("hotel", params.get("hotel").toString()));
        form.add(new Label("tour", params.get("tour").toString()));
        form.add(new Label("user", params.get("user").toString()));
        form.add(new Label("cost", params.get("cost").toString()));
        Button confirmButton = new Button("confirmButton") {
            @Override
            public void onSubmit() {
                orderDao.addNewOrder(params.get("country").toString(),
                        params.get("hotel").toString(),
                        params.get("tour").toString(),
                        params.get("user").toString(),
                        params.get("cost").toString(),
                        "Card");
                setResponsePage(MainPage.class);
            }
        };
        form.add(confirmButton);

        add(form);
    }
}

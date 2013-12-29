/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tour.admin.functions;

import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.model.Model;
import tour.admin.AdminPage;

/**
 *
 * @author Deniss
 */
public final class SearchOrders extends WebPage {

    private TextField<String> username;
    private Form form;
    private Button searchButton;

    public SearchOrders() {
        super();
        form = new Form("form");
        searchButton = new Button("searchButton") {

            @Override
            public void onSubmit() {
                super.onSubmit(); //To change body of generated methods, choose Tools | Templates.
                PageParameters pageParameters = new PageParameters();
		pageParameters.add("userName", username.getModelObject());
		setResponsePage(DeleteOrder.class, pageParameters);
            }

        };
        username = new TextField<String>("usernameText", Model.of(""));
        form.add(username);
        form.add(searchButton);
        add(new BookmarkablePageLink<AdminPage>("AdminPage", AdminPage.class));
        add(form);
    }

    public SearchOrders(PageParameters params) {
        //TODO:  process page parameters
    }
}

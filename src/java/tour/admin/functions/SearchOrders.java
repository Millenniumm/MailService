
package tour.admin.functions;

import tour.menupanel.MenuPanel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.model.Model;
import tour.header.HeaderPanel;


public final class SearchOrders extends WebPage {

    private TextField<String> username;
    private Form form;
    private Button searchButton;

    public SearchOrders() {
        super();
        add(new HeaderPanel("headerPanel"));
        add(new MenuPanel("menuPanel"));
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
        add(form);
    }

    public SearchOrders(PageParameters params) {
        //TODO:  process page parameters
    }
}

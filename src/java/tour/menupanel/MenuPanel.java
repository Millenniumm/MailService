package tour.menupanel;

import tour.account.AccountDAO;
import tour.main.MainPage;
import tour.session.SignInSession;
import tour.signinout.SignOutPage;
import org.apache.wicket.Session;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import tour.profile.Profile;
import tour.admin.functions.CreateOrderForUser;
import tour.admin.functions.SearchOrders;
import tour.order.OrderPage;
import tour.profile.history.UserOrderHistory;

public class MenuPanel extends Panel {

    public MenuPanel(String id) {
        super(id);
        
        add(new Link("homePage") {
           @Override
           public void onClick() {
               setResponsePage(MainPage.class);
           }
        });
        
        add(new Link("orderPage") {
            @Override
            public void onClick() {
                setResponsePage(OrderPage.class);
            }
        });
        
        add(new Link("historyPage") {
           @Override
           public void onClick() {
               setResponsePage(UserOrderHistory.class);
           }
        });
        
        add(new Label("welcome", "Welcome, "));
        add(new Link("profileLink") {
            @Override
            public void onClick() {
                setResponsePage(Profile.class);
            }
        }.add(new Label("name", ((SignInSession) Session.get()).getUser().toString())));
        
        
        Link adminMenu = new Link("adminMenu") {
            @Override
            public void onClick() {

            }
        };
        
        add(new Link("SearchOrders") {
                @Override
                public void onClick() {
                    setResponsePage(SearchOrders.class);
                }
        });      
        
        add(new Link("CreateOrderForUser") {
                @Override
                public void onClick() {
                    setResponsePage(CreateOrderForUser.class);
                }
        });
              
        if (AccountDAO.checkIsAdmin(((SignInSession) Session.get()).getUser().toString())) {
            adminMenu.setVisible(Boolean.FALSE);
            
        }
        
        add(adminMenu);     

        add(new Link("exit") {
            @Override
            public void onClick() {
                setResponsePage(SignOutPage.class);
            }
        });
    }
}

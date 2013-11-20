/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tour.order;

import tour.order.models.Country;
import tour.order.models.OrderObject;
import tour.order.models.Order;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import mail.session.SignInSession;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

/**
 *
 * @author Deniss
 */
public final class OrderPage extends WebPage {

    public OrderPage() {
        super();
        add(new BookmarkablePageLink<mail.main.MainPage>("MainPage", mail.main.MainPage.class));
        Form<?> form = new OrderPageForm("OrderPageForm");
        add(form);
    }

    public OrderPage(PageParameters params) {
        //TODO:  process page parameters

    }

    public final class OrderPageForm extends Form<Void> {

        // choose country[id ,name], after that hotels[id,cointryFkId,name,city,price] can be chousen and tours[id,cointryFkId,name,description,price]
        private OrderDao orderDao = new OrderDao();

        SignInSession session = getMySession();

        private final Map<String, List<String>> hotelOptions = new HashMap<String, List<String>>();
        private final Map<String, List<String>> tourOptions = new HashMap<String, List<String>>();
        private final DropDownChoice<String> countryDropDown;
        private DropDownChoice<String> hotelDropDown;
        private DropDownChoice<String> tourDropDown;
        private String selectedOption;
        Order order = new Order();

        public OrderPageForm(String id) {
            super(id);

            List<Country> countries = orderDao.getCountries();

            for (Country country : countries) {
                List<OrderObject> hotels = orderDao.getHotels(country.getName());
                List<OrderObject> tours = orderDao.getTours(country.getName());

                hotelOptions.put(country.getName(), getNames(hotels,country.getName()));
                tourOptions.put(country.getName(), getNames(tours,country.getName()));
            }

            hotelOptions.put("France", Arrays.asList("Paris - Hilton", "SomeOtherTown - Hilton", "HotTown - Hilton"));
            tourOptions.put("France", Arrays.asList("Bus tour!", "Boat tour!", "Plane Tour"));

            hotelOptions.put("Latvija", Arrays.asList("Riga - Radison", "Jurmala - Radison", "Jelgava - Radison"));
            tourOptions.put("Latvija", Arrays.asList("Bus tour!", "Boat tour!", "Plane Tour"));

            hotelOptions.put("Nigeria", Arrays.asList("Nigger - Mukumakavaka", "Bigger - Mukumakavaka", "BlackTown - Mukumakavaka"));
            tourOptions.put("Nigeria", Arrays.asList("Bus tour! With niggers!", "Boat tour! With alligators!", "Plane Tour! Last in you life!"));

            IModel<List<? extends String>> makeCountryChoises = new AbstractReadOnlyModel<List<? extends String>>() {
                @Override
                public List<String> getObject() {
                    return new ArrayList<String>(hotelOptions.keySet());
                }
            };

            IModel<List<? extends String>> modelTownChoices = new AbstractReadOnlyModel<List<? extends String>>() {
                @Override
                public List<String> getObject() {
                    List<String> models = hotelOptions.get(selectedOption);
                    if (models == null) {
                        models = Collections.emptyList();
                    }
                    return models;
                }
            };

            IModel<List<? extends String>> modelTourChoices = new AbstractReadOnlyModel<List<? extends String>>() {
                @Override
                public List<String> getObject() {
                    List<String> models = tourOptions.get(selectedOption);
                    if (models == null) {
                        models = Collections.emptyList();
                    }
                    return models;
                }
            };

            countryDropDown = new DropDownChoice<String>("countryDropDown",
                    new PropertyModel<String>(this, "selectedOption"), makeCountryChoises);

            hotelDropDown = new DropDownChoice<String>("hotelDropDown",
                    new Model<String>(), modelTownChoices);

            tourDropDown = new DropDownChoice<String>("tourDropDown",
                    new Model<String>(), modelTourChoices);

            hotelDropDown.setOutputMarkupId(true);
            tourDropDown.setOutputMarkupId(true);

            countryDropDown.add(
                    new AjaxFormComponentUpdatingBehavior("onchange") {
                        @Override
                        protected void onUpdate(AjaxRequestTarget target) {
                            target.add(hotelDropDown);
                            target.add(tourDropDown);
                        }
                    }
            );

            add(countryDropDown);
            add(hotelDropDown);
            add(tourDropDown);

        }

        public String getSelectedOption() {
            return selectedOption;
        }

        public void setSelectedOption(String selectedOption) {
            this.selectedOption = selectedOption;
        }

        private SignInSession getMySession() {
            return (SignInSession) getSession();
        }

        @Override
        public final void onSubmit() {
            orderDao.addNewOrder(countryDropDown.getValue(), hotelDropDown.getValue(), tourDropDown.getValue(), session.getUser());
        }

        private List<String> getNames(List<OrderObject> orderObjects,String criteria) {
            List<String> pickedList = new ArrayList<String>();
            for(OrderObject orderObject: orderObjects){
                if(orderObject.getName().equals(criteria)){
                    pickedList.add(orderObject.getName());
                }
            }
            return pickedList;
        }

    }
}

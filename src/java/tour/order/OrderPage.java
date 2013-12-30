/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tour.order;

import tour.order.models.OrderObject;
import tour.order.models.Order;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import mail.menupanel.MenuPanel;
import mail.session.SignInSession;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import tour.header.HeaderPanel;

/**
 *
 * @author Deniss
 */
public final class OrderPage extends WebPage {
// TODO: Make a field for Cost and count it.

    public OrderPage() {
        super();
        add(new HeaderPanel("headerPanel"));
        add(new MenuPanel("menuPanel"));
        Form<?> form = new OrderPageForm("OrderPageForm");
        add(form);
    }

    public OrderPage(PageParameters params) {
        //TODO:  process page parameters

    }

    public final class OrderPageForm extends Form<Void> {

        private Label countryCost;
        private Label hotelCost;
        private Label tourCost;
        private TextField totalCostSum;
        private OrderDao orderDao;
        private final Map<String, List<String>> hotelOptions;
        private final Map<String, List<String>> tourOptions;
        private final DropDownChoice<String> countryDropDown;
        private DropDownChoice<String> hotelDropDown;
        private DropDownChoice<String> tourDropDown;
        private List<OrderObject> hotels;
        private List<OrderObject> tours;
        private List<OrderObject> countries;
        private String selectedOption;
        private String selectedHotel;
        private String selectedTour;
        private String calculatedTotalCostSum;
        private Order order;
        private SignInSession session;
        private Model<String> countryCostModel;
        private Model<String> hotelCostModel;
        private Model<String> tourCostModel;
        private PropertyModel<String> totalCostSumModel;

        public OrderPageForm(String id) {
            super(id);
                   
            countryCostModel = Model.of("");
            hotelCostModel = Model.of("");
            tourCostModel = Model.of("");

            countryCost = new Label("countryCostLabel", countryCostModel);
            hotelCost = new Label("hotelCostLabel", hotelCostModel);
            tourCost = new Label("tourCostLabel", tourCostModel);

            session = getMySession();

            order = new Order();
            orderDao = new OrderDao();
            hotelOptions = new HashMap<String, List<String>>();
            tourOptions = new HashMap<String, List<String>>();

            countries = orderDao.getCountries();
            hotels = orderDao.getHotels();
            tours = orderDao.getTours();

            for (OrderObject country : countries) {
                hotelOptions.put(country.getName(), getNames(hotels, country.getName()));
                tourOptions.put(country.getName(), getNames(tours, country.getName()));
            }

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
                    new PropertyModel<String>(this, "selectedHotel"), modelTownChoices);

            tourDropDown = new DropDownChoice<String>("tourDropDown",
                    new PropertyModel<String>(this, "selectedTour"), modelTourChoices);
            totalCostSumModel = new PropertyModel<String>(this, "calculatedTotalCostSum");
            totalCostSum = new TextField("totalCostSumLabel", totalCostSumModel);

            hotelDropDown.setOutputMarkupId(true);
            tourDropDown.setOutputMarkupId(true);
            countryCost.setOutputMarkupId(true);
            hotelCost.setOutputMarkupId(true);
            tourCost.setOutputMarkupId(true);
            totalCostSum.setOutputMarkupId(true);

            countryDropDown.add(
                    new AjaxFormComponentUpdatingBehavior("onchange") {
                        @Override
                        protected void onUpdate(AjaxRequestTarget target) {
                            target.add(hotelDropDown);
                            target.add(tourDropDown);

                            String cost = getCost(countries, selectedOption);
                            countryCostModel.setObject(cost);
                            hotelCostModel.setObject(getCost(hotels, selectedHotel));
                            tourCostModel.setObject(getCost(tours, selectedTour));
                            totalCostSumModel.setObject(getTotalCostSum());

                            target.add(countryCost);
                            target.add(hotelCost);
                            target.add(tourCost);
                            target.add(totalCostSum);
                        }
                    }
            );
            hotelDropDown.add(
                    new AjaxFormComponentUpdatingBehavior("onchange") {
                        @Override
                        protected void onUpdate(AjaxRequestTarget target) {
                            hotelCostModel.setObject(getCost(hotels, selectedHotel));
                            totalCostSumModel.setObject(getTotalCostSum());
                            target.add(hotelCost);
                            target.add(totalCostSum);
                        }
                    });
            tourDropDown.add(
                    new AjaxFormComponentUpdatingBehavior("onchange") {
                        @Override
                        protected void onUpdate(AjaxRequestTarget target) {
                            tourCostModel.setObject(getCost(tours, selectedTour));
                            totalCostSumModel.setObject(getTotalCostSum());
                            target.add(tourCost);
                            target.add(totalCostSum);
                        }
                    });

            add(countryDropDown);
            add(hotelDropDown);
            add(tourDropDown);
            add(countryCost);
            add(hotelCost);
            add(tourCost);
            add(totalCostSum);

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

        public String getSelectedHotel() {
            return selectedHotel;
        }

        public void setSelectedHotel(String selectedHotel) {
            this.selectedHotel = selectedHotel;
        }

        public String getSelectedTour() {
            return selectedTour;
        }

        public void setSelectedTour(String selectedTour) {
            this.selectedTour = selectedTour;
        }

        public String getCalculatedTotalCostSum() {
            return calculatedTotalCostSum;
        }

        public void setCalculatedTotalCostSum(String calculatedTotalCostSum) {
            this.calculatedTotalCostSum = calculatedTotalCostSum;
        }

        @Override
        public final void onSubmit() {
            orderDao.addNewOrder(getSelectedOption(),
                    getSelectedHotel(), 
                    getSelectedTour(), 
                    session.getUser(), 
                    getTotalCostSum());
        }

        private List<String> getNames(List<OrderObject> orderObjects, String criteria) {
            List<String> pickedList = new ArrayList<String>();
            for (OrderObject orderObject : orderObjects) {
                if (orderObject.getCountry().equals(criteria)) {
                    pickedList.add(orderObject.getName());
                }
            }
            return pickedList;
        }

        private String getCost(List<OrderObject> orderObjects, String name) {
            Double cost = 0.0;
            for (OrderObject object : orderObjects) {
                if (object.getName().equals(name)) {
                    cost = object.getCost();
                }
            }
            return cost.toString();
        }

        private String getTotalCostSum() {

            Double hotelCost = Double.valueOf(getCost(hotels, selectedHotel));
            Double countryCost = Double.valueOf(getCost(countries, selectedOption));
            Double tourCost = Double.valueOf(getCost(tours, selectedTour));
            Double totalCost = hotelCost + countryCost + tourCost;
            return totalCost.toString();
        }

    }
}

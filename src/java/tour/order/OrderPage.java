/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tour.order;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
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
        Form<?> form = new OrderPageForm("OrderPageForm");
        add(form);
    }

    public OrderPage(PageParameters params) {
        //TODO:  process page parameters
    }

    public final class OrderPageForm extends Form<Void> {

        // choose country[id ,name], after that hotels[id,cointryFkId,name,price] can be chousen and tours[id,cointryFkId,name,description,price]
        private OrderDao orderDao = new OrderDao();

        private final Map<String, List<String>> townOptions = new HashMap<String, List<String>>();
        private final Map<String, List<String>> tourOptions = new HashMap<String, List<String>>();
        private final DropDownChoice<String> countryDropDown;
        private DropDownChoice<String> hotelDropDown;
        private DropDownChoice<String> tourDropDown;
        private String selectedOption;
        Order order = new Order();

        public OrderPageForm(String id) {
            super(id);
//TODO: chage data source to DAO !
            townOptions.put("France",Arrays.asList("Paris", "SomeOtherTown", "HotTown"));
            tourOptions.put("France", Arrays.asList("Bus tour!","Boat tour!","Plane Tour"));
            
            townOptions.put("Latvija",Arrays.asList("Riga","Jurmala","Jelgava"));
            tourOptions.put("Latvija",Arrays.asList("Bus tour!","Boat tour!","Plane Tour"));
            
            townOptions.put("Nigeria",Arrays.asList("Nigger","Bigger","BlackTown"));
            tourOptions.put("Nigeria",Arrays.asList("Bus tour! With niggers!","Boat tour! With alligators!","Plane Tour! Last in you life!"));
                    
            IModel<List<? extends String>> makeCountryChoises = new AbstractReadOnlyModel<List<? extends String>>() {
                @Override
                public List<String> getObject() {
                    return new ArrayList<String>(townOptions.keySet());
                }
            };

            IModel<List<? extends String>> modelTownChoices = new AbstractReadOnlyModel<List<? extends String>>() {
                @Override
                public List<String> getObject() {
                    List<String> models = townOptions.get(selectedOption);
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

        @Override
        public final void onSubmit() {
            orderDao.addNewOrder(countryDropDown.getValue(), hotelDropDown.getValue(), tourDropDown.getValue());
        }
    }
}

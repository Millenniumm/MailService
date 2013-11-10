/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tour.order;

import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.PropertyModel;

/**
 *
 * @author Deniss
 */
public final class OrderPage extends WebPage {

    public OrderPage() {
        super();
        add(new OrderPageForm("OrderPageForm"));
    }
    
    public OrderPage(PageParameters params) {
        //TODO:  process page parameters
    }
    
    public final class OrderPageForm extends Form<Void> {

        Order order = new Order();
        
        
        
        
        
        public OrderPageForm(String id) {
            super(id);
            
            
            
        }
        
        @Override
        public final void onSubmit() {
            OrderDao orderDao = new  OrderDao();
            
            orderDao.addNewOrder( countryField.getValue(), hotelField.getValue(), tourField.getValue());
        }
          
    }
}

package dbtest;

import java.util.List;
import junit.framework.TestCase;
import tour.order.OrderDao;
import tour.order.models.Country;
import tour.order.models.OrderObject;

public class DBTest extends TestCase {

    public void testCountrysDB(){
        OrderDao dao =new OrderDao();
        List<Country> countrys = dao.getCountries();
        assertFalse(countrys.isEmpty());
    }
    public void testHotels(){
        OrderDao dao =new OrderDao();
        List<OrderObject> orderobject = dao.getHotels("France");
        assertFalse(orderobject.isEmpty());
    }
    public void testTours(){
        OrderDao dao =new OrderDao();
        List<OrderObject> orderobject = dao.getTours("France");
        assertFalse(orderobject.isEmpty());
    }
}
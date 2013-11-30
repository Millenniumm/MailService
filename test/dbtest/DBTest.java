package dbtest;

import java.util.List;
import junit.framework.TestCase;
import mail.account.Account;
import tour.order.OrderDao;
import tour.order.models.Country;
import tour.order.models.OrderObject;
import mail.account.AccountDAO;

public class DBTest extends TestCase {

    public void testAccounts(){
        AccountDAO accountDao = new AccountDAO();
        List<Account> accounts = accountDao.getAccountList();
        assertFalse(accounts.isEmpty());
    }
    
    public void testCountrysDB(){
        OrderDao dao =new OrderDao();
        List<OrderObject> countrys = dao.getCountries();
        assertFalse(countrys.isEmpty());
    }
    public void testHotels(){
        OrderDao dao =new OrderDao();
        List<OrderObject> orderobject = dao.getHotels();
        assertFalse(orderobject.isEmpty());
    }
    public void testTours(){
        OrderDao dao =new OrderDao();
        List<OrderObject> orderobject = dao.getTours();
        assertFalse(orderobject.isEmpty());
    }
}
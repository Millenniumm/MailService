/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tour.order;

import tour.order.models.Hotel;
import tour.order.models.Country;
import tour.order.models.OrderObject;
import tour.order.models.Tour;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import mail.dbconnection.DBConnection;
import tour.order.models.Order;

/**
 *
 * @author Deniss
 */
public class OrderDao {

    public static void removeOrderById(String id) {
        String insert = "delete from orders where idorders = '" + id + "';";
        DBConnection.executeUpdate(insert);
    }

    public void addNewOrder(String country, String hotel, String tour, String user, String cost, String payment) {
        String delete = "INSERT INTO orders(country,hotel,tour,user,cost,payment) VALUES "
                + "('" + country + "','" + hotel + "','" + tour + "','" + user + "','" + cost + "','"+payment+"')";
        DBConnection.executeUpdate(delete);
    }

    public static List<Order> getOrdersForUser(String user){
        try {
            List<Order> list = new ArrayList<Order>();
            String select = "SELECT * FROM orders where user = '"+user+"';";
            ResultSet query = DBConnection.executeQuery(select);
            while (query.next()) {
                list.add(new Order(query.getString("country"),
                        query.getString("hotel"),
                        query.getString("tour"),
                        query.getString("user"),
                        query.getString("cost"),
                        query.getInt("idorders")));
            }
            return list;
        } catch (Exception e) {
            return null;
        }
    }
    
    public static List<Order> getOrders() {
        try {
            List<Order> list = new ArrayList<Order>();
            String select = "SELECT * FROM orders";
            ResultSet query = DBConnection.executeQuery(select);
            while (query.next()) {
                list.add(new Order(query.getString("country"),
                        query.getString("hotel"),
                        query.getString("tour"),
                        query.getString("user"),
                        query.getString("cost"),
                        query.getInt("idorders")));
            }
            return list;
        } catch (Exception e) {
            return null;
        }
    }

    public List<OrderObject> getCountries() {
        try {
            List<OrderObject> list = new ArrayList<OrderObject>();
            String select = "SELECT * FROM countrys";
            ResultSet query = DBConnection.executeQuery(select);
            while (query.next()) {
                list.add(new Country(query.getString("name"), query.getDouble("cost")));
            }
            return list;
        } catch (Exception e) {
            return null;
        }
    }

    public List<OrderObject> getHotels() {
        try {
            List<OrderObject> list = new ArrayList<OrderObject>();
            String select = "SELECT * FROM hotels";
            ResultSet query = DBConnection.executeQuery(select);
            while (query.next()) {
                list.add(new Hotel(query.getString("name"), query.getDouble("cost"), query.getString("countryFk")));
            }
            return list;
        } catch (Exception e) {
            return null;
        }
    }

    public List<OrderObject> getTours() {
        try {
            List<OrderObject> list = new ArrayList<OrderObject>();
            String select = "SELECT * FROM tours";
            ResultSet query = DBConnection.executeQuery(select);
            while (query.next()) {
                list.add(new Tour(query.getString("name"), query.getDouble("cost"), query.getString("countryFk")));
            }
            return list;
        } catch (Exception e) {
            return null;
        }
    }

}

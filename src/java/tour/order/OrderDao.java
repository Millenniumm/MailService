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

/**
 *
 * @author Deniss
 */
public class OrderDao {

    public void addNewOrder(String country, String hotel, String tour, String user) {
        String insert = "INSERT INTO orders(country,hotel,tour,user) VALUES ('" + country + "','" + hotel + "','" + tour + "','" + user + "')";
        DBConnection.executeUpdate(insert);
    }
    
    public List<Country> getCountries() {
        try {
            List<Country> list = new ArrayList<Country>();
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

    public List<OrderObject> getHotels(String country) {
        try {
            List<OrderObject> list = new ArrayList<OrderObject>();
            String select = "SELECT * FROM hotels where countryFk = '"+ country +"'";
            ResultSet query = DBConnection.executeQuery(select);
            while (query.next()) {
                list.add(new Hotel(query.getString("name"), query.getDouble("cost"), query.getString("countryFk")));
            }
            return list;
        } catch (Exception e) {
            return null;
        }
    }

    public List<OrderObject> getTours(String country) {
        try {
            List<OrderObject> list = new ArrayList<OrderObject>();
            String select = "SELECT * FROM tours where countryFk = '"+ country +"'";
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

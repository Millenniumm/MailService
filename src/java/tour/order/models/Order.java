/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tour.order.models;

/**
 *
 * @author Deniss
 */
public class Order {

    private String country;
    private String hotel;
    private String tour;
    private String user;
    private String cost;
    private Integer id;

    public Order() {
    }

    public Order(String country, String hotel, String tour) {
        this.country = country;
        this.hotel = hotel;
        this.tour = tour;
    }

    public Order(String country, String hotel, String tour, String user, String cost, Integer id) {
        this.country = country;
        this.hotel = hotel;
        this.tour = tour;
        this.user = user;
        this.cost = cost;
        this.id= id;
    }
    
    public void setId(Integer id){
        this.id = id;
    }

    public Integer getId(){
        return id;
    }
    
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getHotel() {
        return hotel;
    }

    public void setHotel(String hotel) {
        this.hotel = hotel;
    }

    public String getTour() {
        return tour;
    }

    public void setTour(String tour) {
        this.tour = tour;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }
    
    public String getOrder(){
        return "    " + this.country+
               "    " + this.hotel+
               "    " + this.tour+
               "    " + this.user+
               "    " + this.cost;
    }

}

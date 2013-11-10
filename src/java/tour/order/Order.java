/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tour.order;

/**
 *
 * @author Deniss
 */
class Order {
    
    private String country;
    private String hotel;
    private String tour;

    public Order() {
    }

    public Order(String country, String hotel, String tour) {
        this.country = country;
        this.hotel = hotel;
        this.tour = tour;
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
     
        
}

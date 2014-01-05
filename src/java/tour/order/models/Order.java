
package tour.order.models;


public class Order {

    private String country;
    private String hotel;
    private String tour;
    private String user;
    private String cost;
    private String payment;
    private Integer id;

    public Order() {
    }
    
    public Order(String country, String hotel, String tour, String user, String cost){
        this.country = country;
        this.hotel = hotel;
        this.tour = tour;
        this.user = user;
        this.cost = cost;
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
   
    
    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }
    
    public void setId(Integer id){
        this.id = id;
    }

    public Integer getId(){
        return id;
    }
    
    public String getCountry() {
        return country == null ? "" : country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getHotel() {
    return hotel == null ? "" : hotel;    
    }

    public void setHotel(String hotel) {
        this.hotel = hotel;
    }

    public String getTour() {
        return tour == null ? "" : tour;
    }

    public void setTour(String tour) {
        this.tour = tour;
    }

    public String getUser() {
        return user == null ? "" : user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getCost() {
        return cost == null ? "" : cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }
    
    public String getOrder(){
        return "    " + getCountry()+
               "    " + getHotel()+
               "    " + getTour()+
               "    " + getUser()+
               "    " + getCost();
    }
}

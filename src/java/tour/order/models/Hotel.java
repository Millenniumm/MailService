

package tour.order.models;

public class Hotel extends OrderObject{

    private String name;
    private Double cost;
    private String country;

    public Hotel(String name, Double cost, String country) {
        this.name = name;
        this.cost = cost;
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
    
    
}

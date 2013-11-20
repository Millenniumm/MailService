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
public class Tour extends OrderObject{

    private String name;
    private Double cost;
    private String country;

    public Tour(String name, Double cost, String country) {
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

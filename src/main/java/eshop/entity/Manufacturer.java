package eshop.entity;

/**
 * @author Евгений
 */
public class Manufacturer {

    int id;
    String country;
    String name;

    public Manufacturer(int id,String country, String name) {
        this.id=id;
        this.country = country;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

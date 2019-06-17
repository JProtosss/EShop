package eshop.entity;

import java.util.Objects;

/**
 * @author Евгений
 */
public class Manufacturer {

    private int id;
    private String country;
    private String name;

    public Manufacturer(int id, String name, String country) {
        this.id = id;
        this.country = country;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Manufacturer that = (Manufacturer) o;
        return id == that.id &&
                country.equals(that.country) &&
                name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, country, name);
    }
}

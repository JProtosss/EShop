package eshop.entity;


import java.util.Objects;

/**
 * @author Евгений
 */
public class Product {
    private int id;
    private String name;
    private String price;
    private int amount;
    private String description;
    private String image;
    private Manufacturer manufacturer;
    private Type type;

    public Product(int id, String name, String price, int amount, String description, String image, Manufacturer manufacturer, Type type) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.amount = amount;
        this.description = description;
        this.image = image;
        this.manufacturer = manufacturer;
        this.type = type;
    }

    public Product(String name, String price, int amount, String description, Manufacturer manufacturer, Type type) {
        this.name = name;
        this.price = price;
        this.amount = amount;
        this.description = description;
        this.manufacturer = manufacturer;
        this.type = type;
    }

    public Product() {
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id &&
                amount == product.amount &&
                name.equals(product.name) &&
                price.equals(product.price) &&
                Objects.equals(description, product.description) &&
                Objects.equals(image, product.image) &&
                manufacturer.equals(product.manufacturer) &&
                type.equals(product.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, amount, description, image, manufacturer, type);
    }
}

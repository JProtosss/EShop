package eshop.entity;

/**
 * @author Евгений
 */
public class Type {

    int id;
    String type;

    public Type(int id,String type) {
        this.id=id;
        this.type = type;
    }

    public Type(String type)
    {
        this.type=type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

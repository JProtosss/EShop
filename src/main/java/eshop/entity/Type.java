package eshop.entity;

import java.util.Objects;

/**
 * @author Евгений
 */
public class Type {

    private int id;
    private String type;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Type type1 = (Type) o;
        return id == type1.id &&
                type.equals(type1.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type);
    }
}

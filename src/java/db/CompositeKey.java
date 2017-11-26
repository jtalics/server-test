package db;

import com.google.common.base.Objects;

import javax.persistence.Entity;
import java.io.Serializable;

@Entity
public class CompositeKey implements Serializable {

    private String name; // a.k.a. title
    private int yearReleased;

    public CompositeKey() {
        // For hibernate
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompositeKey that = (CompositeKey) o;
        return yearReleased == that.yearReleased &&
                Objects.equal(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name, yearReleased);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYearReleased() {
        return yearReleased;
    }

    public void setYearReleased(int yearReleased) {
        this.yearReleased = yearReleased;
    }
}


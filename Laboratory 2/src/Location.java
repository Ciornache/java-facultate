import java.util.Objects;
import java.util.Scanner;

public class Location {

    private _location type;
    private String name;
    private position pos;

    public  Location(_location type, String name, position pos)
    {
        setType(type);
        setName(name);
        setPos(pos);
    }

    public _location getType() {
        return type;
    }

    public void setType(_location type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public position getPos() {
        return pos;
    }

    public void setPos(position pos) {
        this.pos = pos;
    }

    @Override
    public String toString() {
        return "location{" +
                "type=" + type +
                ", name='" + name + '\'' +
                ", pos=" + pos +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return type == location.type && Objects.equals(name, location.name) && Objects.equals(pos, location.pos);
    }

}

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Person implements Node, Comparable {

    List<Node> associatedPlaces;

    private final String name;
    char [] birthDate;

    public char[] getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(char[] birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", birthDate=" + Arrays.toString(birthDate) +
                ", personType=" + personType +
                '}';
    }

    private final PersonType personType;

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public PersonType getType() {
        return this.personType;
    }

    public Person(String name, PersonType type, char[] birthDate) throws InvalidNameException {

        boolean ok = validateName(name);
        System.out.println("IS OK " + ok);
        if(!ok)
            throw new InvalidNameException("The name must only contain letters from a to z, lower or upper case.");
        birthDate = new char[25];
        this.birthDate = birthDate;
        this.name = name;
        this.personType = type;
        associatedPlaces = new ArrayList<>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(name, person.name) && Arrays.equals(birthDate, person.birthDate) && personType == person.personType;
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(name, personType);
        result = 31 * result + Arrays.hashCode(birthDate);
        return result;
    }

    protected boolean validateName(String name)
    {
        for(int i = 0;i < name.length(); ++i)
        {
            char c = name.charAt(i);
            if((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z'))
                continue;
            return false;
        }
        return true;
    }

    void addEdge(Node node) {
        for(Node copyNode : associatedPlaces) {
            if(copyNode.equals(node))
                return;
        }
        associatedPlaces.add(node);
    }

    @Override
    public int compareTo(Object o) {
        Person p2 = (Person) o;
        if(!this.getType().equals(p2.getType()))
            return this.getType().compareTo(p2.getType());
        if(!this.getName().equals(p2.getName()))
            return this.getName().compareTo(p2.getName());
        return Integer.compare(this.hashCode(), p2.hashCode());
    }
}

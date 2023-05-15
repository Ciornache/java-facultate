import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class Company implements Node, Comparable {

    private String name;

    List<Person> employees;
    public String getName() {
        return name;
    }

    @Override
    public PersonType getType() {
        return null;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Person> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Person> employees) {
        this.employees = employees;
    }

    @Override
    public String toString() {
        return "Company{" +
                "name='" + name + '\'' +
                ", employees=" + employees +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return Objects.equals(name, company.name) && Objects.equals(employees, company.employees);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, employees);
    }

    Company(String name) {
        employees = new ArrayList<>();
        this.name = name;
    }

    public boolean addEmployee(Person person)
    {
        boolean ok = checkEmployee(person);
        if(ok)
            return false;

        employees.add(person);
        return true;
    }

    private boolean checkEmployee(Person employee)
    {
        for(Person emp : employees)
        {
            if(emp.equals(employee))
                return true;
        }
        return false;
    }

    @Override
    public int compareTo(Object o) {
        Company c2 = (Company) o;
        if(!c2.getName().equals(this.getName()))
            return c2.getName().compareTo(this.getName());
        return Integer.compare(c2.getEmployees().size(), this.getEmployees().size());
    }


}

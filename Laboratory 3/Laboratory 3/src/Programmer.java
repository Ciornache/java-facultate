import java.util.List;

public class Programmer extends Person{

    private double salary;

    String[] knownLanguages;

    List<Designer> designerList;

    public Programmer(String name, PersonType type, char[] birthDate, double salary, String [] knownLanguages) throws InvalidNameException {
        super(name, type, birthDate);
        this.salary = salary;
        this.knownLanguages = knownLanguages;
    }

    private void printSkills()
    {
        System.out.println("This programmer has mastered this programming languages");
        for(String str : knownLanguages)
            System.out.print(str + " ");
        System.out.println();
    }
}

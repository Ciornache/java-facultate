import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputHandler {

    Scanner scanner;

    Network network;
    private int n; ///Number of persons
    private int m; ///Number of relationships
    private int comp; ///Number of companies

    static public int task;

    InputHandler() {
        scanner = new Scanner(System.in);
        network = new Network();
    }

    public void initializeProblemInstance() {
        read();
    }

    private void read() {
        readPeople();
        readRelationships();
        readCompanies();
        network.mergeLists();
        task = scanner.nextInt();
    }

    private void readPeople() {
        n = scanner.nextInt();
        for(int i = 1;i <= n; ++i) {
            int type = scanner.nextInt();
            String name = scanner.next();
            String birth = scanner.next();
            char [] birthDate = birth.toCharArray();

            PersonType personType = null;

            PersonType[] enumValues = PersonType.values();
            for(PersonType per : enumValues){
                if(per.ordinal() == type)
                    personType = per;
            }

            switch(type)
            {
                case 0:
                    try {
                        Person person = new Person(name, personType, birthDate);
                        network.addPerson(person);
                    }
                    catch(InvalidNameException e) {
                        i--;
                        break;
                    }
                    break;

                case 1:
                    double salary = scanner.nextDouble();
                    try {
                        Person person = new Designer(name, personType, birthDate, salary);
                        network.addPerson(person);
                    }
                    catch(InvalidNameException e) {
                        i--;
                        break;
                    }
                    break;

                case 2:
                    salary = scanner.nextDouble();
                    scanner.nextLine();
                    String knownLang = scanner.nextLine();
                    try {
                        Person person = new Programmer(name, personType, birthDate, salary, parseLanguages(knownLang));
                        network.addPerson(person);
                    }
                    catch(InvalidNameException e)
                    {
                        i--;
                        break;
                    }
            }
        }
    }

    private void readRelationships()
    {
        m = scanner.nextInt();
        for(int i = 1;i <= m; ++i) {
//            System.out.println(i);
            String name1 = scanner.next(),
                    name2 = scanner.next();

            boolean ok = network.addEdge(name1, name2);
//            System.out.println("HERE " + name1 + " " + name2);
            if(ok)
                i--;

        }
    }

    private String[] parseLanguages(String s)
    {
        String currentLanguage = null;
        List<String> listLang = new ArrayList<>();

        for(int i = 0;i < s.length(); ++i) {
            if(s.charAt(i) == ' ') {
                listLang.add(currentLanguage);
                currentLanguage = " ";
            }
            else
                currentLanguage += s.charAt(i);
        }

        assert currentLanguage != null;
        if(!currentLanguage.equals(" "))
            listLang.add(currentLanguage);

        String[] answer = new String[listLang.size() + 2];

        int ind = 0;
        for(String str : listLang)
            answer[++ind] = str;

        return answer;
    }

    private void readCompanies()
    {
        comp = scanner.nextInt();
        for(int cp = 1;cp <= comp; ++cp)
        {
            String name = scanner.next();

            Company currCompany;

            boolean ok = network.addCompany(currCompany = new Company(name));
            if(ok)
            {
                cp--;
                continue;
            }

            int numberOfEmployees = scanner.nextInt();

            for(int emp = 1;emp <= numberOfEmployees; ++emp)
            {
                String personName = scanner.next();
                Person person = Network.getPersonByName(personName);
                if(person == null)
                {
                    emp--;
                    continue;
                }
                ok = currCompany.addEmployee(person);
                if(!ok)
                    emp--;
            }

        }
    }

}

/*

Input Test

10
0
QuandalePringles
1945-11-11
2
Modofk
1020-04-22
420420420.69
Modolion
2
DoctorMichaelMorbius
0-00-00
1000000000000.3
C++ Python Morbing-- Koitlin Huskell React NodeJS
1
DreamyBull
1998-04-12
1000000.6969
1
Nissan
1944-01-01
696969669.100000
0
XinJinPing
1960-05-23
2
TheWoke
2000-12-12
999999.999
YouAreNotAllowedToPlayGamesMoreThan2HoursPerWeek
0
Sucre
1990-02-11
2
Felix
1996-03-25
12345678.90
C# BridgeIncident Java CSS HTML JavaScript
0
CalistratBogza
1500-10-21
10
DreamyBull Nissan
XinJinPing TheWoke
Felix CalistratBogza
DreamyBull Modofk
QuandalePringles Modofk
Sucre Felix
TheWoke Felix
CalistratBogza QuandalePringles
Modofk DoctorMichaelMorbius
Nissan Modofk
5
Tesla
0
Twitter
2
DreamyBull
Nissan
Youtube
3
Felix
Sucre
CalistratBogza
OscorpTowers
2
DoctorMichaelMorbius
Modofk
Pringles
1
QuandalePringles
1

 */
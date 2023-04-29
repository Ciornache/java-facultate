import java.util.Scanner;

public class Director {
    
    public static void manageClasses(String name)
    {
        if(compareStrings(name, "Homework")) {
            Homework.solveHomework();
        }
        else if(compareStrings(name, "Compulsory")) {
            Compulsory.solveCompulsory();
        }
        else if(compareStrings(name, "Bonus"))
        {
            Scanner scaner = new Scanner(System.in);
            int task = scaner.nextInt();
            if(task == 1)
                Bonus.solveBonus1();
            else if(task == 2)
                Bonus.solveBonus2();
            else
                System.out.print("INVALID INPUT");
        }
        else
            System.out.print("INVALID INPUT");
    }

    public static boolean compareStrings(String firstString, String secondString)
    {
        if(firstString.length() != secondString.length())
            return false;
        for(int i = 0;i < firstString.length(); ++i)
        {
            if(firstString.charAt(i) != secondString.charAt(i))
                return false;
        }
        return true;
    }

}
import java.lang.Math;
import java.util.Arrays;
public class Compulsory {

    private final static int MaxNumber = 100;
    private static BaseConverter baseConverter;

    public static void solveCompulsory() {

        baseConverter = new BaseConverter();

        printMessage("Hello World");
//        System.out.printf(languages[0]);
        long n = generateRandomNumber();
        n = applyOperations(n);
        n = getCifraControl(n);

        System.out.println(n);
//        System.out.printf("Willy-nilly, this semester I will learn " + languages[(int)(n)] + "\n");

        for (String language : languages)
            System.out.printf("Willy-nilly, this semester I will learn " + language + "\n");

    }
    private static void printMessage(String message) {
        System.out.println(message);
    }
    private static final String[] languages = {"C", "C++", "C#", "Python", "Go", "Rust", "JavaScript", "PHP", "Swift", "Java"};

    public static int generateRandomNumber() {
        double randomBuffer = Math.random();
        return (int)(randomBuffer * MaxNumber);
    }

    private static long applyOperations(long x) {
        x *= 3;
//        System.out.println(baseConverter.convertFromBaseTwo("10101"));
//        System.out.println(baseConverter.convertFromBaseSixteen("FF"));
        x += baseConverter.convertFromBaseTwo("10101");
        x += baseConverter.convertFromBaseSixteen("FF");
        x *= 6;
        return x;
    }

    private static long getCifraControl(long x) {
        return x % 9;
    }

}

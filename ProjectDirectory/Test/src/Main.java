import java.util.ArrayList;
import java.util.List;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {

    public static class TestA
    {
        List<Integer> list = new ArrayList<>();
    }

    static List<TestA> currentList = new ArrayList<>();

    public static void main(String[] args) {

        TestA element = new TestA();

        currentList.add(element);

        element.list.add(2);

        System.out.println(currentList.get(0).list.size());

        for (int i = 1; i <= 5; i++) {


            System.out.println("i = " + i);
        }
    }
}
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class Main {

    public static InputHandler inputHandler;

    public static Network network;

    public static void main(String[] args) throws URISyntaxException, IOException {
        inputHandler = new InputHandler();
        network = new Network();
        inputHandler.initializeProblemInstance();
        System.out.println(network);
        if(InputHandler.task == 1) {
            List<Integer> articulationPoints = Network.findArticulationPoints();
            System.out.println("The articulation points are");
            for (Integer articulationPoint : articulationPoints) {
                Person person = Network.getPersonByHashCode(articulationPoint);
                assert person != null;
                System.out.println(person.getName());
            }
        }
        else {
            List<List<Integer>> biconex = network.findBiconnectedComponents();
            System.out.println("The biconnected components are");
            for(List<Integer> li : biconex)
            {
                System.out.println(li.size());
                for(Integer i : li)
                    System.out.print(Network.getPersonByHashCode(i).getName() + " ");
                System.out.println();
            }
        }

        List<Person> persons = Network.getPersons();
        for(int i = 0;i < persons.size(); ++i)
        {
            if(persons.get(i).getType().equals(PersonType.DESIGNER))
            {
                Designer designerPerson = (Designer) persons.get(i);
                designerPerson.openDesignerTraining();
            }
        }

    }
}
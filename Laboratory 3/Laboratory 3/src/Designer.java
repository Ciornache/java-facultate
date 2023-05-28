import java.awt.Desktop;
import java.net.URI;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class Designer extends Person{

    private double salary;

    public Designer(String name, PersonType type, char[] birthDate, double salary) throws InvalidNameException {
        super(name, type, birthDate);
        this.salary = salary;
    }

    public void openDesignerTraining() throws URISyntaxException, IOException {
        Desktop d = Desktop.getDesktop();
        d.browse(new URI("https://profs.info.uaic.ro/~acf/java/labs/lab_03.html"));
    }
}

package org.example;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.Objects;

public class ViewCommand implements Command {

    @Override
    public void execute() throws IOException, InvalidDocumentName {
        String title = Main.scanner.nextLine();
        Document document = Main.catalog.getDocument(title);
        if(Objects.isNull(document)) {
            throw new InvalidDocumentName("The document doesn't exist");
        }

        Desktop desktop = Desktop.getDesktop();
        String path = document.getPath();
        if(path.charAt(0) == 'h') {
            desktop.browse(URI.create(path));
        }
        else {
            desktop.open(new File(path));
        }
    }


}

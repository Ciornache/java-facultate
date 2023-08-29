package org.example;

import javax.print.Doc;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.DoubleConsumer;

public class Catalog {

    private final Map<Document, Boolean> documentHandler;

    private final CommandHandler commandHandler;

    private List<Document> documentList;

    Catalog() {
        commandHandler = new CommandHandler();
        documentList = new ArrayList<>();
        documentHandler = new HashMap<>();
    }

    public void process(String command) throws IncorrectCommandName, IOException, InvalidDocumentName {
        commandHandler.execute(command);
    }

    public CommandHandler getCommandHandler() {
        return commandHandler;
    }

    public List<Document> getDocumentList() {
        return documentList;
    }

    public void setDocumentList(List<Document> documentList) {
        this.documentList = documentList;
    }

    public Document getDocument(String title){
        for(Document doc : documentList) {
            String documentTitle = doc.getDocInfo().getTitle();
            if(documentTitle.equals(title))
                return doc;
        }
        return null;
    }

    public boolean isDocumentIn(Document doc) {
        return documentHandler.containsKey(doc);
    }

    public void setDocument(Document doc) {
        documentHandler.put(doc, true);
    }

    @Override
    public String toString() {
        return "Catalog{" +
                "documentHandler=" + documentHandler +
                ", commandHandler=" + commandHandler +
                ", documentList=" + documentList +
                '}';
    }
}

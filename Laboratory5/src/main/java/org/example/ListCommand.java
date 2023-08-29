package org.example;

import java.util.List;

public class ListCommand implements Command{
    @Override
    public void execute() {
        List<Document> documentList = Main.catalog.getDocumentList();
        for(Document doc : documentList)
            System.out.println(doc);
    }



}


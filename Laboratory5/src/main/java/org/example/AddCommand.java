package org.example;

import java.util.ArrayList;
import java.util.List;

public class AddCommand implements Command {

    private String text;

    private void readText() {
        text = Main.scanner.nextLine();
    }

    @Override
    public void execute() throws RedundantObjectException, DocumentFormatException {

        this.readText();
        boolean ok = checkCommand(text);
        if(!ok){
            throw new DocumentFormatException("Invalid Document Format");
        }

        Document document = new Document();
        int number = 0;

        for(int i = 0;i < text.length(); ++i) {

            StringBuilder currentText = new StringBuilder();
            while(i < text.length())
            {
                char c = text.charAt(i);
                if(c == ',')
                    break;
                currentText.append(c);
                i++;
            }
//            System.out.println(currentText);
            if(currentText.toString().equals("null"))
                continue;
            else
            {
                document.setParameter(currentText.toString(), number);
                number++;
            }
        }
        ok = Main.catalog.isDocumentIn(document);
        if(!ok) {
            List<String> tags = readTags();
            document.setId(Main.catalog.getDocumentList().size() + 1);
            document.setTagList(tags);
            Main.catalog.getDocumentList().add(document);

        }
        else {
            throw new RedundantObjectException("Document Already in Catalog");
        }
    }

    private boolean checkCommand(String text) {
        int comma = 0;
        for(int i = 0;i < text.length(); ++i){
            if(text.charAt(i) == ',')
                comma++;
        }
        return comma == 7;
    }

    private List<String> readTags() {

        String tags = Main.scanner.nextLine();
        List<String> tagList = new ArrayList<>();

        for(int i = 0;i < tags.length(); ++i) {
            StringBuilder tag = new StringBuilder();
            while(i < tags.length())
            {
                char c = tags.charAt(i);
                if(c == ' ')
                    break;
                tag.append(c);
                i++;
            }
            tagList.add(tag.toString());
        }

        return tagList;
    }
}


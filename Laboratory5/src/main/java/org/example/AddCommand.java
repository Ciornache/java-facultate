package org.example;

public class AddCommand implements Command {

    private String text;

    private void readText() {
        text = Main.scanner.nextLine();
    }

    @Override
    public void execute() {

        this.readText();
        boolean ok = checkCommand(text);
        if(!ok)
            return;

        Document document = new Document();
        int number = 0;

        for(int i = 0;i < text.length(); ++i) {

            StringBuilder currentText = new StringBuilder();
            while(i < text.length())
            {
                char c = text.charAt(i);
                if(c == ' ')
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
            Main.catalog.getDocumentList().add(document);
            Main.catalog.setDocument(document);
            document.setId(Main.catalog.getDocumentList().size());
        }
    }

    private boolean checkCommand(String text) {
        int spaces = 0;
        for(int i = 0;i < text.length(); ++i){
            if(text.charAt(i) == ' ')
                spaces++;
        }
        return spaces == 7;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}


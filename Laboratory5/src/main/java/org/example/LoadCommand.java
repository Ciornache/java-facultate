package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.SplittableRandom;

public class LoadCommand implements Command{

    @Override
    public void execute() throws IOException {
        int index = Main.scanner.nextInt();
        ObjectMapper objectMapper = new ObjectMapper();

        StringBuilder path = new StringBuilder("D:/Documents/java-facultate/Laboratory5/cataloage/catalog");
        path.append(Integer.toString(index));
        path.append(".json");

        Catalog catalog = objectMapper.readValue(new File(String.valueOf(path)), Catalog.class);
        Main.changeCatalog(catalog);
    }


}

package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;

public class SaveCommand implements Command {


    @Override
    public void execute() throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

        StringBuilder path = new StringBuilder("D:/Documents/java-facultate/Laboratory5/cataloage/catalog");
        path.append(Integer.toString((int) (Math.random() * 1000)));
        path.append(".json");
        System.out.println(path);
        objectMapper.writeValue(new File(String.valueOf(path)), Main.catalog);
    }


}


///D:\Documents\java-facultate\Laborator 5
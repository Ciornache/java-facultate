package org.example;

public class IncorrectCommandName extends Exception{
    public IncorrectCommandName(String errorMessage) {
        super(errorMessage);
    }
}